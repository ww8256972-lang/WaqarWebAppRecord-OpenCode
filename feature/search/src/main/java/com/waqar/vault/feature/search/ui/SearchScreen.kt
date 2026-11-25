package com.waqar.vault.feature.search.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun SearchScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("SearchScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module search implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
