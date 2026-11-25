package com.waqar.vault.feature.theme.vm
import androidx.lifecycle.ViewModel
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ThemeViewModel @Inject constructor(private val dataStore: DataStore<Preferences>) : ViewModel() {
    private val themeKey = stringPreferencesKey("waqar_theme")
    private val _theme = MutableStateFlow("Liquid Glass Dark")
    val theme: StateFlow<String> = _theme.asStateFlow()
    init { viewModelScope.launch { dataStore.data.map { it[themeKey] ?: "Liquid Glass Dark" }.collect { _theme.value = it } } }
    fun setTheme(t: String) { viewModelScope.launch { dataStore.edit { it[themeKey] = t } } }
    fun setHighContrast(enabled: Boolean) { /* apply theme preference */ }
}