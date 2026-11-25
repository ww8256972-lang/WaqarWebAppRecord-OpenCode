package com.waqar.vault.feature.bulk.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.domain.model.Customer
import androidx.paging.PagingData
import com.waqar.vault.domain.model.Customer
import com.waqar.vault.domain.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
data class BulkState(val selected: Set<String> = emptySet(), val tag: String = "", val message: String? = null)
@HiltViewModel
class BulkViewModel @Inject constructor(private val repo: CustomerRepository) : ViewModel() {
    val customers: Flow<PagingData<Customer>> = repo.pagingCustomers()
    private val _state = MutableStateFlow(BulkState())
    val state: StateFlow<BulkState> = _state.asStateFlow()
    fun toggle(id: String) {
        val cur = _state.value.selected
        _state.value = _state.value.copy(selected = if (id in cur) cur - id else cur + id)
    }
    fun clear() { _state.value = _state.value.copy(selected = emptySet()) }
    fun setTag(t: String) { _state.value = _state.value.copy(tag = t) }
    fun deleteSelected() {
        viewModelScope.launch {
            _state.value.selected.forEach { repo.softDelete(it) }
            _state.value = BulkState(message = "Deleted ${_state.value.selected.size} records")
        }
    }
}