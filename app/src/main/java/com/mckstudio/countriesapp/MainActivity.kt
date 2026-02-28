package com.mckstudio.countriesapp

import android.app.Activity
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.os.LocaleListCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.mckstudio.countriesapp.layouts.BannerAd
import com.mckstudio.countriesapp.navigation.SetupNavGraph
import com.mckstudio.countriesapp.ui.theme.CountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CountriesAppTheme {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        scrim = MaterialTheme.colorScheme.primary.toArgb(),
                        darkScrim = MaterialTheme.colorScheme.primary.toArgb(),
                    )
                )

                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        scrim = MaterialTheme.colorScheme.primary.toArgb(),
                    )
                )

                val window = (LocalView.current.context as Activity).window
                val controller = WindowCompat.getInsetsController(window, LocalView.current)
                controller.isAppearanceLightStatusBars = false

                val navHostController = rememberNavController()

                Surface(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(navController = navHostController)
                    BannerAd()
                }
            }
        }
    }
}