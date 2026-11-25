package com.waqar.vault.feature.excelimport.vm
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.domain.model.Customer
import com.waqar.vault.domain.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.util.UUID
import javax.inject.Inject
data class ImportState(val customers: List<Customer> = emptyList(), val progress: Float = 0f, val status: String = "Idle", val undoCustomer: Customer? = null)
@HiltViewModel
class ExcelImportViewModel @Inject constructor(@ApplicationContext private val ctx: Context, private val customerRepo: CustomerRepository) : ViewModel() {
 private val _state = MutableStateFlow(ImportState())
 val state: StateFlow<ImportState> = _state.asStateFlow()
 fun import(uri: Uri) {
  viewModelScope.launch {
   _state.value = _state.value.copy(status = "Parsing...", progress = 0f)
   val parsed = withContext(Dispatchers.IO) { parseFile(ctx, uri) { p -> _state.value = _state.value.copy(progress = p) } }
   customerRepo.upsertAll(parsed)
   _state.value = ImportState(customers = parsed, progress = 1f, status = "${parsed.size} Customers Parsed & Synced to Firebase")
  }
 }
 fun remove(id: String) {
  val cur = _state.value
  val target = cur.customers.firstOrNull { it.id == id } ?: return
  viewModelScope.launch {
   customerRepo.softDelete(id)
   _state.value = cur.copy(customers = cur.customers.filterNot { it.id == id }, undoCustomer = target)
  }
 }
 fun undoRemove() {
  val u = _state.value.undoCustomer ?: return
  viewModelScope.launch {
   customerRepo.upsert(u)
   _state.value = _state.value.copy(customers = _state.value.customers + u, undoCustomer = null)
  }
 }
 fun deleteAll() {
  viewModelScope.launch {
   customerRepo.deleteAll()
   _state.value = ImportState(status = "All records deleted")
  }
 }
 private fun parseFile(ctx: Context, uri: Uri, onProgress: (Float) -> Unit): List<Customer> {
  val input: InputStream = ctx.contentResolver.openInputStream(uri) ?: return emptyList()
  val name = uri.toString().lowercase()
  val rows = mutableListOf<List<String>>()
  if (name.endsWith(".csv")) {
   input.bufferedReader().useLines { lines -> lines.forEach { rows.add(it.split(",")) } }
  } else {
   try {
    val wb = org.dhatim.fastexcel.reader.ReadableWorkbook(input)
    val sheet = wb.firstSheet
    sheet.openStream().use { rs -> rs.forEach { r -> rows.add((0 until r.cellCount).map { c -> r.getCellText(c) ?: "" }) } }
   } catch (e: Exception) { input.close(); return emptyList() }
  }
  if (rows.isEmpty()) return emptyList()
  val header = rows.first().map { it.trim().lowercase() }
  fun col(vararg keys: String): Int = header.indexOfFirst { h -> keys.any { k -> h.contains(k) } }
  val shopIdx = col("shop", "business", "dukan", "store")
  val personIdx = col("person", "customer", "owner", "name")
  val phoneIdx = col("mobile", "phone", "contact")
  val waIdx = col("whatsapp", "wa")
  val cityIdx = col("city", "town")
  val catIdx = col("category", "type")
  val dataRows = rows.drop(1)
  val out = mutableListOf<Customer>()
  dataRows.forEachIndexed { idx, r ->
   val shop = if (shopIdx >= 0) r.getOrElse(shopIdx) { "" } else ""
   val person = if (personIdx >= 0) r.getOrElse(personIdx) { "" } else ""
   val phone = normalize(if (phoneIdx >= 0) r.getOrElse(phoneIdx) { "" } else "")
   val wa = if (waIdx >= 0) normalize(r.getOrElse(waIdx) { "" }) else phone
   val city = if (cityIdx >= 0) r.getOrElse(cityIdx) { "" } else null
   val cat = if (catIdx >= 0) r.getOrElse(catIdx) { "" } else null
   var s = shop; var p = person
   if (s.contains(" - ") && p.isBlank()) { val parts = s.split(" - ", limit = 2); s = parts.getOrElse(0) { s }; p = parts.getOrElse(1) { p } }
   if (phone.isBlank() && s.isBlank() && p.isBlank()) return@forEachIndexed
   out.add(Customer(id = UUID.randomUUID().toString(), serialCode = "WQR-${(idx + 1).toString().padStart(6, '0')}", shopName = s.ifBlank { "Unknown Shop" }, personName = p.ifBlank { "Unknown" }, phoneE164 = phone.ifBlank { "+910000000000" }, whatsappE164 = wa.ifBlank { null }, hasWhatsapp = wa.isNotBlank(), city = city?.ifBlank { null }, category = cat?.ifBlank { null }))
   onProgress((idx + 1).toFloat() / dataRows.size.coerceAtLeast(1))
  }
  return out
 }
 private fun normalize(raw: String): String {
  val digits = raw.filter { it.isDigit() }
  if (digits.isEmpty()) return ""
  val d = if (digits.length == 10) "91$digits" else if (digits.startsWith("91") && digits.length == 12) digits else digits
  return "+$d"
 }
}