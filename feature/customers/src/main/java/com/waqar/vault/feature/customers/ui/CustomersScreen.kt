package com.waqar.vault.feature.customers.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.waqar.vault.feature.customers.vm.CustomersViewModel
@Composable
fun CustomersScreen(vm: CustomersViewModel = hiltViewModel()) {
 val items = vm.pager.collectAsLazyPagingItems()
 Column(Modifier.fillMaxSize()) {
  Text("100K Customer Directory", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(16.dp))
  androidx.compose.foundation.lazy.LazyColumn {
   items(items.itemCount) { idx ->
    val c = items[idx]
    if (c != null) ListItem(headlineContent = { Text(c.shopName) }, supportingContent = { Text("${c.personName} • ${c.phoneE164}") })
   }
  }
 }
}