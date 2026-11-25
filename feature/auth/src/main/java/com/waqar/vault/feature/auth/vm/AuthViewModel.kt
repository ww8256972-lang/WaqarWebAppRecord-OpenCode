package com.waqar.vault.feature.auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.security.auth.NativeVault
import com.waqar.vault.security.keystore.VaultKeyProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.security.SecureRandom
import javax.inject.Inject

data class AuthState(val isLoading: Boolean = false, val isAuthenticated: Boolean = false, val error: String? = null, val lockoutSeconds: Int = 0)

@HiltViewModel
class AuthViewModel @Inject constructor(private val keyProvider: VaultKeyProvider) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private var failedAttempts = 0
    private var lockoutUntil = 0L

    fun login(username: String, password: CharArray) {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            if (now < lockoutUntil) {
                _state.value = _state.value.copy(lockoutSeconds = ((lockoutUntil - now) / 1000).toInt())
                return@launch
            }
            if (username != "waqar") { recordFailure(); return@launch }
            _state.value = _state.value.copy(isLoading = true)
            try {
                val salt = ByteArray(16).also { SecureRandom().nextBytes(it) }
                val derived = NativeVault.deriveKey(String(password), salt)
                // Zero password char array
                password.fill('\u0000')
                // Verify against Firebase Auth (online) or cached verifier (offline)
                // In production, call Firebase Auth signInWithEmailAndPassword
                _state.value = AuthState(isAuthenticated = true)
                failedAttempts = 0
            } catch (e: Exception) {
                recordFailure()
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    private fun recordFailure() {
        failedAttempts++
        val lockout = when {
            failedAttempts >= 9 -> 300_000L
            failedAttempts >= 6 -> 60_000L
            failedAttempts >= 3 -> 15_000L
            else -> 5_000L
        }
        lockoutUntil = System.currentTimeMillis() + lockout
    }

    fun logout() {
        _state.value = AuthState()
        failedAttempts = 0
        lockoutUntil = 0L
    }
}