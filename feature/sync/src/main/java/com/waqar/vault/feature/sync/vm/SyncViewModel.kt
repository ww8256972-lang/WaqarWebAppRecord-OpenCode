package com.waqar.vault.feature.sync.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.core.sync.SyncScheduler
import com.waqar.vault.database.WaqarDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
data class SyncState(val pendingCount: Int = 0, val isSyncing: Boolean = false, val message: String? = null)
@HiltViewModel
class SyncViewModel @Inject constructor(@ApplicationContext private val ctx: android.content.Context, private val db: WaqarDatabase) : ViewModel() {
    private val _state = MutableStateFlow(SyncState())
    val state: StateFlow<SyncState> = _state.asStateFlow()
    init { viewModelScope.launch { db.mutationOutboxDao().pending().let { _state.value = _state.value.copy(pendingCount = it.size) } } }
    fun triggerSync() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isSyncing = true)
            SyncScheduler.schedule(ctx)
            val pending = db.mutationOutboxDao().pending()
            _state.value = SyncState(pendingCount = pending.size, isSyncing = false, message = "Sync scheduled")
        }
    }
}