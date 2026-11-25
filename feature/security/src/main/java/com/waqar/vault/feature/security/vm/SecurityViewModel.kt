package com.waqar.vault.feature.security.vm
import androidx.lifecycle.ViewModel
import com.waqar.vault.security.ndk.AntiTamper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SecurityViewModel @Inject constructor() : ViewModel() {
    val domains = listOf("L1 UI/Identity","L2 NDK","L3 Encrypted Storage","L4 Network/Firebase","L5 OS/CI")
    fun checkTracerPid(): Boolean = runCatching { AntiTamper.checkTracerPid() }.getOrDefault(false)
    fun checkFrida(): Boolean = runCatching { AntiTamper.checkFridaThreads() && AntiTamper.checkMapsForFrida() }.getOrDefault(false)
}