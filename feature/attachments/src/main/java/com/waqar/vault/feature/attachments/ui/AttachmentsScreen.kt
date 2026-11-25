package com.waqar.vault.feature.attachments.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.attachments.vm.AttachmentsViewModel
@Composable
fun AttachmentsScreen(vm: AttachmentsViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 var name by remember { mutableStateOf("") }
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Attachment Vault", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  OutlinedTextField(value = state.customerId, onValueChange = vm::setCustomer, label = { Text("Customer ID") })
  OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("File Name") })
  Button(onClick = { vm.add(name, "/data/$name"); name = "" }) { Text("Add Attachment") }
  Spacer(Modifier.height(12.dp))
  state.items.forEach { a -> Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Column(Modifier.padding(12.dp)) { Text(a.fileName); Text(a.localPath, style = MaterialTheme.typography.bodySmall) } } }
 }
}