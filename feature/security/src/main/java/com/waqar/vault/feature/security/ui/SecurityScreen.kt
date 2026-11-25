package com.waqar.vault.feature.security.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun SecurityScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("SecurityScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module security implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
