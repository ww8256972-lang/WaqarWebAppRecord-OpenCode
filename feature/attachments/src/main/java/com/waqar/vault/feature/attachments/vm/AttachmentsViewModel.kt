package com.waqar.vault.feature.attachments.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.database.WaqarDatabase
import com.waqar.vault.database.entity.AttachmentEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
data class AttachmentsState(val items: List<AttachmentEntity> = emptyList(), val customerId: String = "")
@HiltViewModel
class AttachmentsViewModel @Inject constructor(private val db: WaqarDatabase) : ViewModel() {
    private val _state = MutableStateFlow(AttachmentsState())
    val state: StateFlow<AttachmentsState> = _state.asStateFlow()
    fun setCustomer(c: String) { _state.value = _state.value.copy(customerId = c); load() }
    private fun load() { viewModelScope.launch { _state.value = _state.value.copy(items = db.attachmentDao().byCustomer(_state.value.customerId)) } }
    fun add(name: String, path: String) {
        viewModelScope.launch {
            db.attachmentDao().upsert(AttachmentEntity(id = UUID.randomUUID().toString(), customerId = _state.value.customerId, projectId = null, fileName = name, mimeType = null, localPath = path))
            load()
        }
    }
}