package com.waqar.vault.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CurvedGlassSideMenu(navigate: (String) -> Unit) {
    // Side menu with Bulk Excel Import (#801) pinned at top and collapsible groups for all 20 modules
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp, 16.dp, 16.dp, 0.dp))
            .background(Color(0x940F172A)),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { navigate("excel") }) { Text("Bulk Excel Import #801") }
                Button(onClick = { navigate("dashboard") }) { Text("Dashboard") }
                Button(onClick = { navigate("customers") }) { Text("100K Directory") }
            }
        }
        // Collapsible module groups
        val modules = listOf(
            listOf("M01 Launch", "M02 Navigation", "M03 Dashboard"),
            listOf("M04 Customers", "M05 Profile", "M06 Communication", "M07 Projects", "M08 Domains"),
            listOf("M09 Search", "M10 Bulk Ops", "M11 Followups", "M12 Attachments", "M13 Sync", "M14 Reports"),
            listOf("M15 QR/OCR", "M16 Admin", "M17 Theme", "M18 Security", "M19 Perf", "M20 Updates")
        )
        val expanded = remember { mutableStateOf(setOf<Int>()) }
        modules.forEachIndexed { groupIdx, group ->
            item {
                Column {
                    Text("Module Group ${groupIdx + 1}")
                    group.forEach { m ->
                        Button(onClick = { navigate(m) }, modifier = Modifier.fillMaxWidth().padding(4.dp)) { Text(m) }
                    }
                    Spacer(Modifier.height(4.dp))
                }
            }
        }
    }
}