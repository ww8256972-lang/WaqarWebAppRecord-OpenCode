package com.waqar.vault.feature.updates.vm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject
@HiltViewModel
class UpdatesViewModel @Inject constructor(private val dataStore: DataStore<Preferences>) : ViewModel() {
    val latestVersion = stringPreferencesKey("waqar_latest_version")
}