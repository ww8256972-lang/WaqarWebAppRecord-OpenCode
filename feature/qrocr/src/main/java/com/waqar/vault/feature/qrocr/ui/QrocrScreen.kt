package com.waqar.vault.feature.qrocr.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.qrocr.vm.QrocrViewModel
@Composable
fun QrocrScreen(vm: QrocrViewModel = hiltViewModel()) {
 var content by remember { mutableStateOf("") }
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("QR & OCR Tools", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  OutlinedTextField(value = content, onValueChange = { content = it }, label = { Text("QR Content") })
  Button(onClick = {}) { Text("Generate QR") }
  Spacer(Modifier.height(8.dp))
  Text("OCR scanning available via CameraX + ML Kit")
 }
}