package com.waqar.vault.feature.admin.vm
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AdminViewModel @Inject constructor() : ViewModel() {
    val roles = listOf("SUPER_ADMIN", "MANAGER", "OPERATOR", "READ_ONLY")
}