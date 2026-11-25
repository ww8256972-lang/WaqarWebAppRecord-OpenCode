package com.waqar.vault.feature.domains.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.domains.vm.DomainsViewModel
@Composable
fun DomainsScreen(vm: DomainsViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Domain & Server Vault", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  OutlinedTextField(value = state.name, onValueChange = vm::setName, label = { Text("Domain Name") })
  OutlinedTextField(value = state.customerId, onValueChange = vm::setCustomer, label = { Text("Customer ID") })
  Button(onClick = vm::add) { Text("Add Domain") }
  Spacer(Modifier.height(12.dp))
  state.domains.forEach { d -> Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Column(Modifier.padding(12.dp)) { Text(d.domainName, style = MaterialTheme.typography.titleMedium); Text("Expiry: ${d.domainExpiry ?: "N/A"}") } } }
 }
}