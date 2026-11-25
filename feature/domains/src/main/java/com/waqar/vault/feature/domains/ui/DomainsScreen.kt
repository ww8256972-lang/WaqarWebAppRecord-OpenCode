package com.waqar.vault.feature.domains.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun DomainsScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("DomainsScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module domains implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
