package com.waqar.vault.feature.security.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.security.vm.SecurityViewModel
@Composable
fun SecurityScreen(vm: SecurityViewModel = hiltViewModel()) {
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Security Vault", style = MaterialTheme.typography.headlineSmall)
  Text("TracerPid: ${vm.checkTracerPid()}", style = MaterialTheme.typography.bodyMedium)
  Text("Frida: ${vm.checkFrida()}", style = MaterialTheme.typography.bodyMedium)
  vm.domains.forEach { d -> Text("$d: OK") }
 }
}