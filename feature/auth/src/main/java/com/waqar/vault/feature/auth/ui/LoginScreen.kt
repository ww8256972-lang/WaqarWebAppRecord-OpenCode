package com.waqar.vault.feature.auth.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
@Composable
fun LoginScreen(onSuccess: () -> Unit) {
 var username by remember { mutableStateOf("") }
 var password by remember { mutableStateOf("") }
 var error by remember { mutableStateOf<String?>(null) }
 val scope = rememberCoroutineScope()
 Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFF070B14), Color(0xFF0F172A))))) {
  Column(Modifier.align(Alignment.Center).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
   Text("Waqar Web & App Record", color = Color(0xFF22D3EE), style = MaterialTheme.typography.headlineSmall)
   Spacer(Modifier.height(24.dp))
   OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
   Spacer(Modifier.height(12.dp))
   OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())
   Spacer(Modifier.height(16.dp))
   Button(onClick = { scope.launch { if (username == "waqar" && password.isNotEmpty()) onSuccess() else error = "Invalid credentials" } }) { Text("SIGN IN") }
   error?.let { Text(it, color = Color.Red) }
  }
 }
}