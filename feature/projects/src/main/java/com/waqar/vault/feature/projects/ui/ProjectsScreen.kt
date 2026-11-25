package com.waqar.vault.feature.projects.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun ProjectsScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("ProjectsScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module projects implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
