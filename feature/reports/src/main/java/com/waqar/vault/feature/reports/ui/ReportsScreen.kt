package com.waqar.vault.feature.reports.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.reports.vm.ReportsViewModel
@Composable
fun ReportsScreen(vm: ReportsViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Reports & Export", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Customers: ${state.customerCount}")
  Text("Projects: ${state.projectCount}")
  Text("Follow-ups: ${state.followUpCount}")
  Spacer(Modifier.height(12.dp))
  Button(onClick = vm::generateSummary) { Text("Generate Summary") }
  state.generatedReport?.let { Text(it, style = MaterialTheme.typography.bodySmall) }
 }
}