package com.waqar.vault.feature.attachments.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun AttachmentsScreen() {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("AttachmentsScreen", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Module attachments implementation.", style = MaterialTheme.typography.bodyMedium)
 }
}
