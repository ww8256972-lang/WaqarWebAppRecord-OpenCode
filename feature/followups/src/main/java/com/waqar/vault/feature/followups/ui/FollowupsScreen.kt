package com.waqar.vault.feature.followups.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.followups.vm.FollowupsViewModel
@Composable
fun FollowupsScreen(vm: FollowupsViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Follow-Up Planner", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  OutlinedTextField(value = state.customerId, onValueChange = vm::setCustomer, label = { Text("Customer ID") })
  OutlinedTextField(value = state.note, onValueChange = vm::setNote, label = { Text("Note") })
  Button(onClick = vm::add) { Text("Schedule Follow-Up") }
  Spacer(Modifier.height(12.dp))
  state.tasks.forEach { t -> Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) { Column { Text(t.note ?: "Follow-up"); Text(t.customerId, style = MaterialTheme.typography.bodySmall) } Button(onClick = { vm.complete(t.id) }) { Text("Done") } } } }
 }
}