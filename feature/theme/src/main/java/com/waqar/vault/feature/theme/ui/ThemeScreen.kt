package com.waqar.vault.feature.theme.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.theme.vm.ThemeViewModel
@Composable
fun ThemeScreen(vm: ThemeViewModel = hiltViewModel()) {
 val theme by vm.theme.collectAsState()
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Liquid Glass Theme Engine", style = MaterialTheme.typography.headlineSmall)
  FilterChip(selected = true, onClick = {}, label = { Text("Midnight Obsidian #070B14") })
  FilterChip(selected = false, onClick = { vm.setTheme("Cyan Violet") }, label = { Text("Cyan Violet Aurora") })
  Text("AGSL Shaders, Haze blur, Reduced-motion supported")
 }
}