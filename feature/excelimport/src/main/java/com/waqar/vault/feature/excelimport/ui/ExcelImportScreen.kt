package com.waqar.vault.feature.excelimport.ui
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.domain.model.Customer
import com.waqar.vault.feature.excelimport.vm.ExcelImportViewModel
import com.waqar.vault.feature.excelimport.vm.ImportState
@Composable
fun ExcelImportScreen(vm: ExcelImportViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> uri?.let { vm.import(it) } }
 var showDeleteAll by remember { mutableStateOf(false) }
 val snackbar = remember { SnackbarHostState() }
 LaunchedEffect(state.undoCustomer) {
  state.undoCustomer?.let {
   val r = snackbar.showSnackbar("Record removed", "Undo", SnackbarDuration.Short)
   if (r == SnackbarResult.ActionPerformed) vm.undoRemove()
  }
 }
 Scaffold(snackbarHost = { SnackbarHost(snackbar) }) { pad ->
  Column(Modifier.padding(pad).padding(16.dp)) {
   Text("Bulk Excel Import (#801)", style = MaterialTheme.typography.headlineSmall)
   Spacer(Modifier.height(8.dp))
   Text(state.status)
   if (state.progress > 0f && state.progress < 1f) LinearProgressIndicator(progress = { state.progress }, modifier = Modifier.fillMaxWidth())
   Spacer(Modifier.height(8.dp))
   Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    Button(onClick = { launcher.launch("*/*") }) { Text("Pick Excel/CSV") }
    OutlinedButton(onClick = { showDeleteAll = true }) { Text("Delete All") }
   }
   Spacer(Modifier.height(12.dp))
   state.customers.forEach { c -> CustomerCard(c, onDelete = { vm.remove(c.id) }); Spacer(Modifier.height(8.dp)) }
  }
 }
 if (showDeleteAll) {
  AlertDialog(onDismissRequest = { showDeleteAll = false }, title = { Text("Delete all records?") }, text = { Text("This will remove all imported customers.") },
   confirmButton = { TextButton(onClick = { vm.deleteAll(); showDeleteAll = false }) { Text("Delete") } },
   dismissButton = { TextButton(onClick = { showDeleteAll = false }) { Text("Cancel") } })
 }
}
@Composable
fun CustomerCard(c: Customer, onDelete: () -> Unit) {
 Card(Modifier.fillMaxWidth()) {
  Column(Modifier.padding(12.dp)) {
   Text(c.shopName, style = MaterialTheme.typography.titleMedium)
   Text("${c.personName} • ${c.phoneE164}")
   Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    TextButton(onClick = {}) { Text("WA Chat") }
    TextButton(onClick = {}) { Text("WA Call") }
    TextButton(onClick = {}) { Text("Call") }
    TextButton(onClick = onDelete) { Text("Delete") }
   }
  }
 }
}