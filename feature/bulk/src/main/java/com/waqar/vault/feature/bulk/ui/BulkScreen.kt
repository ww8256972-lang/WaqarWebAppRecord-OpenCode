package com.waqar.vault.feature.bulk.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.waqar.vault.feature.bulk.vm.BulkViewModel
@Composable
fun BulkScreen(vm: BulkViewModel = hiltViewModel()) {
 val state by vm.state.collectAsState()
 val items = vm.customers.collectAsLazyPagingItems()
 Column(Modifier.fillMaxSize()) {
  Text("Bulk Operations", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(16.dp))
  Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
   Text("Selected: ${state.selected.size}")
   Button(onClick = vm::deleteSelected, enabled = state.selected.isNotEmpty()) { Text("Delete Selected") }
   OutlinedButton(onClick = vm::clear) { Text("Clear") }
  }
  androidx.compose.foundation.lazy.LazyColumn {
   items(items.itemCount) { idx ->
    val c = items[idx]
    if (c != null) {
     Row(Modifier.fillMaxWidth().padding(8.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
      Checkbox(checked = c.id in state.selected, onCheckedChange = { vm.toggle(c.id) })
      Column { Text(c.shopName); Text(c.personName, style = MaterialTheme.typography.bodySmall) }
     }
    }
   }
  }
 }
}