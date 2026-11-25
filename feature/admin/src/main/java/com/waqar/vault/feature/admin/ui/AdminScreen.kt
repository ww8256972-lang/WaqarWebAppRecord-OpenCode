package com.waqar.vault.feature.admin.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.admin.vm.AdminViewModel
@Composable
fun AdminScreen(vm: AdminViewModel = hiltViewModel()) {
 val roles by vm.roles.let { androidx.compose.runtime.mutableStateOf(it) }
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Admin & RBAC", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  roles.forEach { role -> FilterChip(selected = false, onClick = {}, label = { Text(role) }); Spacer(Modifier.height(4.dp)) }
 }
}