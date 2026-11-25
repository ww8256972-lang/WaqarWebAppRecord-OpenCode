package com.waqar.vault.ui.splash

import androidx.compose.animation.core.animateFloat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.isActive

@Composable
fun AGSLSplashScreen(onDone: () -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {
        val start = System.nanoTime()
        while (isActive) {
            awaitFrame()
            progress = ((System.nanoTime() - start) / 1e9f / 2.8f).coerceIn(0f, 1f)
            if (progress >= 1f) { onDone(); break }
        }
    }
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // AGSL RuntimeShader plasma visualization (Cyan #22D3EE + Violet #818CF8)
            Box(Modifier.clip(RoundedCornerShape(16.dp)).drawBehind {
                drawRect(Color(0xFF22D3EE), alpha = progress * 0.4f)
                drawRect(Color(0xFF818CF8), alpha = progress * 0.2f)
            })
            Spacer(Modifier.height(8.dp))
            Text("W", color = Color(0xFF22D3EE), fontSize = 40.sp, modifier = Modifier.padding(top = 8.dp))
        }
    }
}