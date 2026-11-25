package com.waqar.vault.feature.updates.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.updates.vm.UpdatesViewModel
@Composable
fun UpdatesScreen(vm: UpdatesViewModel = hiltViewModel()) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Private Updates", style = MaterialTheme.typography.headlineSmall)
        Text("GitHub Release check & SHA-256 verification (see F-761-F-800)")
    }
}