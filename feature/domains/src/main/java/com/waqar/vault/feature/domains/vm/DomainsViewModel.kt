package com.waqar.vault.feature.domains.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.database.WaqarDatabase
import com.waqar.vault.database.entity.DomainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
data class DomainsState(val domains: List<DomainEntity> = emptyList(), val name: String = "", val customerId: String = "")
@HiltViewModel
class DomainsViewModel @Inject constructor(private val db: WaqarDatabase) : ViewModel() {
    private val _state = MutableStateFlow(DomainsState())
    val state: StateFlow<DomainsState> = _state.asStateFlow()
    init { viewModelScope.launch { db.domainDao().allFlow().collect { _state.value = _state.value.copy(domains = it) } } }
    fun setName(n: String) { _state.value = _state.value.copy(name = n) }
    fun setCustomer(c: String) { _state.value = _state.value.copy(customerId = c) }
    fun add() {
        viewModelScope.launch {
            val s = _state.value
            db.domainDao().upsert(DomainEntity(id = UUID.randomUUID().toString(), customerId = s.customerId.ifBlank { null }, domainName = s.name))
            _state.value = s.copy(name = "")
        }
    }
}