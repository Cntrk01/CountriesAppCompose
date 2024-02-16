package com.mckstudio.countriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mckstudio.countriesapp.layouts.BannerAd
import com.mckstudio.countriesapp.navigation.SetupNavGraph
import com.mckstudio.countriesapp.ui.theme.CountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesAppTheme {
                val navHostController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Burada cagırınca görünme durumu düzeliyor
                    //AppBar(imageId = R.drawable.icon_app_bar)
                    SetupNavGraph(navController = navHostController)
                    BannerAd()
                }
            }
        }
    }
}