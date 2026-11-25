package com.waqar.vault.feature.performance.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.performance.vm.PerformanceViewModel
@Composable
fun PerformanceScreen(vm: PerformanceViewModel = hiltViewModel()) {
    val info by androidx.compose.runtime.mutableStateOf("n/a")
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Memory & Perf Telemetry", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("RAM: ${vm.getRamInfo()}")
        Spacer(Modifier.height(8.dp))
        Text("Page Benchmark: ${vm.benchmark50Page()} ms")
    }
}