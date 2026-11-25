package com.waqar.vault.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import com.waqar.vault.ui.splash.AGSLSplashScreen
import com.waqar.vault.ui.navigation.CurvedGlassSideMenu

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val nav = rememberNavController()
                    val showSplash = remember { mutableStateOf(true) }
                    LaunchedEffect(Unit) {
                        if (showSplash.value) showSplash.value = false
                    }
                    if (showSplash.value) AGSLSplashScreen(onDone = { showSplash.value = false; nav.navigate("login") })
                    else AppNavGraph(nav)
                }
            }
        }
    }
}