package com.waqar.vault.feature.projects.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.projects.vm.ProjectsViewModel
@Composable
fun ProjectsScreen(vm: ProjectsViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Web & App Projects", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  OutlinedTextField(value = state.title, onValueChange = vm::setTitle, label = { Text("Title") })
  OutlinedTextField(value = state.customerId, onValueChange = vm::setCustomer, label = { Text("Customer ID") })
  Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
   FilterChip(selected = state.projectType == "Website", onClick = { vm.setType("Website") }, label = { Text("Website") })
   FilterChip(selected = state.projectType == "Android App", onClick = { vm.setType("Android App") }, label = { Text("Android App") })
  }
  Button(onClick = vm::add) { Text("Add Project") }
  Spacer(Modifier.height(12.dp))
  state.projects.forEach { p -> Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Column(Modifier.padding(12.dp)) { Text(p.title, style = MaterialTheme.typography.titleMedium); Text("${p.projectType} • ${p.stage}") } } }
 }
}