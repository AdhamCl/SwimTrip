@file:OptIn(ExperimentalFoundationApi::class)

package com.example.swimtrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.swimtrip.screens.HomeScreen
import com.example.swimtrip.ui.theme.MayaBlue
import com.example.swimtrip.ui.theme.SwimTripTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val swimViewModel: SwimViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwimTripTheme {
                SetStatusBarColor(MayaBlue)
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    HomeScreen(swimViewModel)
                }
            }
        }
    }
}


@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}


