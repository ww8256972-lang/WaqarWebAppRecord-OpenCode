package com.waqar.vault.feature.reports.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.database.WaqarDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
data class ReportsState(val customerCount: Int = 0, val projectCount: Int = 0, val followUpCount: Int = 0, val generatedReport: String? = null)
@HiltViewModel
class ReportsViewModel @Inject constructor(private val db: WaqarDatabase) : ViewModel() {
    private val _state = MutableStateFlow(ReportsState())
    val state: StateFlow<ReportsState> = _state.asStateFlow()
    init {
        viewModelScope.launch {
            val c = db.customerDao().countFlow()
            c.collect { count -> _state.value = _state.value.copy(customerCount = count) }
        }
    }
    fun generateSummary() {
        viewModelScope.launch {
            val projects = db.projectDao().allFlow().let { 0 }
            _state.value = _state.value.copy(generatedReport = "Customers: ${_state.value.customerCount}\nProjects: ${_state.value.projectCount}\nFollow-ups: ${_state.value.followUpCount}")
        }
    }
}