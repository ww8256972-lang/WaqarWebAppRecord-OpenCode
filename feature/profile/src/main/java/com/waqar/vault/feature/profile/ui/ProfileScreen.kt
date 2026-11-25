package com.waqar.vault.feature.profile.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun ProfileScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("ProfileScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module profile implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
