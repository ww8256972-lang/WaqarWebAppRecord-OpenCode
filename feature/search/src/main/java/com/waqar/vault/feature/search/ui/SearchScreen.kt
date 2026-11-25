package com.waqar.vault.feature.search.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.waqar.vault.feature.search.vm.SearchViewModel
@Composable
fun SearchScreen(vm: SearchViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 Column(Modifier.fillMaxSize().padding(16.dp)) {
  Text("Ultra-Fast Search", style = MaterialTheme.typography.headlineSmall)
  Spacer(Modifier.height(8.dp))
  OutlinedTextField(value = state.query, onValueChange = vm::setQuery, label = { Text("Search shop/person/phone") }, modifier = Modifier.fillMaxWidth())
  if (state.isSearching) LinearProgressIndicator(Modifier.fillMaxWidth())
  Spacer(Modifier.height(8.dp))
  state.results.forEach { c -> Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) { Column(Modifier.padding(12.dp)) { Text(c.shopName, style = MaterialTheme.typography.titleMedium); Text("${c.personName} • ${c.phoneE164}") } } }
 }
}