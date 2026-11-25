package com.waqar.vault.feature.followups.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.database.WaqarDatabase
import com.waqar.vault.database.entity.FollowUpEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
data class FollowupsState(val tasks: List<FollowUpEntity> = emptyList(), val customerId: String = "", val note: String = "")
@HiltViewModel
class FollowupsViewModel @Inject constructor(private val db: WaqarDatabase) : ViewModel() {
    private val _state = MutableStateFlow(FollowupsState())
    val state: StateFlow<FollowupsState> = _state.asStateFlow()
    init { viewModelScope.launch { db.followUpDao().pendingFlow().collect { _state.value = _state.value.copy(tasks = it) } } }
    fun setCustomer(c: String) { _state.value = _state.value.copy(customerId = c) }
    fun setNote(n: String) { _state.value = _state.value.copy(note = n) }
    fun add() {
        viewModelScope.launch {
            val s = _state.value
            db.followUpDao().upsert(FollowUpEntity(id = UUID.randomUUID().toString(), customerId = s.customerId, scheduledAt = System.currentTimeMillis() + 86400000, note = s.note))
            _state.value = s.copy(note = "")
        }
    }
    fun complete(id: String) {
        viewModelScope.launch { db.followUpDao().upsert(_state.value.tasks.first { it.id == id }.copy(completedAt = System.currentTimeMillis())) }
    }
}