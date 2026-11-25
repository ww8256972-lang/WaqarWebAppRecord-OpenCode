package com.waqar.vault.feature.profile.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.domain.model.Customer
import com.waqar.vault.domain.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
data class ProfileState(val customer: Customer? = null, val isEditing: Boolean = false, val notes: String = "", val isLoading: Boolean = false, val error: String? = null)
@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: CustomerRepository) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()
    fun load(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val c = repo.getById(id)
            _state.value = ProfileState(customer = c, notes = c?.tags?.joinToString(",") ?: "", isLoading = false)
        }
    }
    fun toggleEdit() { _state.value = _state.value.copy(isEditing = !_state.value.isEditing) }
    fun updateNotes(n: String) { _state.value = _state.value.copy(notes = n) }
    fun save() {
        viewModelScope.launch {
            val c = _state.value.customer ?: return@launch
            repo.upsert(c.copy(tags = _state.value.notes.split(",").map { it.trim() }.filter { it.isNotBlank() }))
            _state.value = _state.value.copy(isEditing = false)
        }
    }
}