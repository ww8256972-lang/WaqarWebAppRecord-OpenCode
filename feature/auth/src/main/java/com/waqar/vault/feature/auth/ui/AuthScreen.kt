package com.waqar.vault.feature.auth.ui
import android.os.SystemClock
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.auth.vm.AuthViewModel
import com.waqar.vault.security.auth.NativeVault
import kotlinx.coroutines.launch
@Composable
fun AuthScreen(onSuccess: () -> Unit, vm: AuthViewModel = hiltViewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(CharArray(0)) }
    val state by vm.state.collectAsState()
    val scope = rememberCoroutineScope()
    Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFF070B14), Color(0xFF0F172A))))) {
        Column(Modifier.align(Alignment.Center).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Waqar Web & App Record", color = Color(0xFF22D3EE), style = MaterialTheme.typography.headlineSmall)
            if (state.lockoutSeconds > 0) Text("Lockout: ${state.lockoutSeconds}s", color = Color(0xFFFF5252))
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = String(password), onValueChange = { password = it.toCharArray() }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())
            Button(onClick = { scope.launch { vm.login(username, password); onSuccess() } }, enabled = state.isAuthenticated || !state.isLoading) { Text("SIGN IN") }
            state.error?.let { Text(it, color = Color(0xFFFF5252)) }
        }
    }
}