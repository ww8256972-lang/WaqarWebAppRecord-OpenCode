package com.waqar.vault.feature.bulk.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun BulkScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("BulkScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module bulk implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
