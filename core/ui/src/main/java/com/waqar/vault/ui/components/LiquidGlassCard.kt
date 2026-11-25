package com.waqar.vault.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun LiquidGlassCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
 Box(modifier = modifier.clip(RoundedCornerShape(20.dp)).background(Color(0x940F172A)).border(1.dp, Brush.linearGradient(listOf(Color(0x5938BDF8), Color(0x59818CF8))), RoundedCornerShape(20.dp))) { content() }
}