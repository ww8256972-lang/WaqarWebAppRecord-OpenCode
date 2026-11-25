package com.waqar.vault.feature.communication.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.communication.vm.CommunicationViewModel
@Composable
fun CommunicationScreen(vm: CommunicationViewModel = hiltViewModel()) {
 var phone by remember { mutableStateOf("") }
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Communication Hub", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(12.dp))
  OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone (E.164)") })
  Spacer(Modifier.height(12.dp))
  Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
   Button(onClick = { vm.openWhatsAppChat(phone) }) { Text("WA Chat") }
   Button(onClick = { vm.openWhatsAppCall(phone) }) { Text("WA Call") }
   Button(onClick = { vm.dial(phone) }) { Text("Dial") }
  }
 }
}