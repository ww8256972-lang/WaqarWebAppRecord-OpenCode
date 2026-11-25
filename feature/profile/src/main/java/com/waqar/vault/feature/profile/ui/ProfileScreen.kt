package com.waqar.vault.feature.profile.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.profile.vm.ProfileViewModel
@Composable
fun ProfileScreen(vm: ProfileViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Customer Profile 360", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(12.dp))
  state.customer?.let { c ->
   Card(Modifier.fillMaxWidth()) {
    Column(Modifier.padding(16.dp)) {
     Text(c.shopName, style = MaterialTheme.typography.titleLarge)
     Text(c.personName)
     Text(c.phoneE164)
     c.city?.let { Text("City: $it") }
     c.category?.let { Text("Category: $it") }
     if (state.isEditing) {
      OutlinedTextField(value = state.notes, onValueChange = vm::updateNotes, label = { Text("Tags (comma separated)") })
      Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
       Button(onClick = vm::save) { Text("Save") }
       OutlinedButton(onClick = vm::toggleEdit) { Text("Cancel") }
      }
     } else {
      Button(onClick = vm::toggleEdit) { Text("Edit") }
     }
    }
   }
  } ?: Text("No customer loaded", style = MaterialTheme.typography.bodyMedium)
 }
}