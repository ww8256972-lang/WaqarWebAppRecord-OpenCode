package com.waqar.vault.feature.sync.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun SyncScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("SyncScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module sync implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
