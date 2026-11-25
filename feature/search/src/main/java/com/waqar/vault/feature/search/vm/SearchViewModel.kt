package com.waqar.vault.feature.search.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waqar.vault.database.WaqarDatabase
import com.waqar.vault.database.entity.CustomerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
data class SearchState(val query: String = "", val results: List<CustomerEntity> = emptyList(), val isSearching: Boolean = false)
@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(private val db: WaqarDatabase) : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()
    private val queryFlow = MutableStateFlow("")
    init {
        viewModelScope.launch {
            queryFlow.debounce(150).distinctUntilChanged().collect { q ->
                if (q.isBlank()) { _state.value = _state.value.copy(results = emptyList(), isSearching = false); return@collect }
                _state.value = _state.value.copy(isSearching = true)
                val res = db.customerDao().search(q)
                _state.value = _state.value.copy(results = res, isSearching = false)
            }
        }
    }
    fun setQuery(q: String) { _state.value = _state.value.copy(query = q); queryFlow.value = q }
}