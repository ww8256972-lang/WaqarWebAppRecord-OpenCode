package com.waqar.vault.feature.dashboard.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun DashboardScreen(onOpenExcel: () -> Unit) {
 Column(Modifier.fillMaxSize().background(Color(0xFF070B14)).padding(16.dp)) {
  Text("Executive Dashboard", color = Color.White, style = MaterialTheme.typography.headlineMedium)
  Spacer(Modifier.height(16.dp))
  Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
   KpiCard("Customers", "0", Modifier.weight(1f)); KpiCard("Projects", "0", Modifier.weight(1f))
  }
  Spacer(Modifier.height(12.dp))
  Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
   KpiCard("Follow-ups", "0", Modifier.weight(1f)); KpiCard("Sync", "Idle", Modifier.weight(1f))
  }
  Spacer(Modifier.height(24.dp))
  Button(onClick = onOpenExcel) { Text("Bulk Excel Import (#801)") }
 }
}
@Composable
fun KpiCard(title: String, value: String, modifier: Modifier = Modifier) {
 Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))) {
  Column(Modifier.padding(16.dp)) { Text(title, color = Color(0xFF94A3B8)); Text(value, color = Color.White, style = MaterialTheme.typography.headlineSmall) }
 }
}