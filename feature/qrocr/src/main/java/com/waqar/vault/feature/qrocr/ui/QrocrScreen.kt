package com.waqar.vault.feature.qrocr.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun QrocrScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("QrocrScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module qrocr implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
