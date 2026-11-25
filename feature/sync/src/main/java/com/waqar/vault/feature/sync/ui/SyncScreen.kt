package com.waqar.vault.feature.sync.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.sync.vm.SyncViewModel
@Composable
fun SyncScreen(vm: SyncViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Sync Engine", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  Text("Pending mutations: ${state.pendingCount}")
  state.message?.let { Text(it) }
  Spacer(Modifier.height(12.dp))
  Button(onClick = vm::triggerSync, enabled = !state.isSyncing) { Text(if (state.isSyncing) "Syncing..." else "Trigger Sync") }
 }
}