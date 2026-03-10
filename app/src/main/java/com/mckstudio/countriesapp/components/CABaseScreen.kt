package com.mckstudio.countriesapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mckstudio.countriesapp.ui.components.CATopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CABaseScreen(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
    title: String = "Country App",
    backClick: (() -> Unit)? = null,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CATopBar(
                title = title,
                onBackClick = backClick,
            )
        },
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}