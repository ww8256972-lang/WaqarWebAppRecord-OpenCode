package com.waqar.vault.feature.projects.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.database.WaqarDatabase
import com.waqar.vault.database.entity.ProjectEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
data class ProjectsState(val projects: List<ProjectEntity> = emptyList(), val title: String = "", val customerId: String = "", val projectType: String = "Website")
@HiltViewModel
class ProjectsViewModel @Inject constructor(private val db: WaqarDatabase) : ViewModel() {
    private val _state = MutableStateFlow(ProjectsState())
    val state: StateFlow<ProjectsState> = _state.asStateFlow()
    init { viewModelScope.launch { db.projectDao().allFlow().collect { _state.value = _state.value.copy(projects = it) } } }
    fun setTitle(t: String) { _state.value = _state.value.copy(title = t) }
    fun setCustomer(c: String) { _state.value = _state.value.copy(customerId = c) }
    fun setType(t: String) { _state.value = _state.value.copy(projectType = t) }
    fun add() {
        viewModelScope.launch {
            val s = _state.value
            db.projectDao().upsert(ProjectEntity(id = UUID.randomUUID().toString(), customerId = s.customerId, projectType = s.projectType, title = s.title))
            _state.value = s.copy(title = "")
        }
    }
}