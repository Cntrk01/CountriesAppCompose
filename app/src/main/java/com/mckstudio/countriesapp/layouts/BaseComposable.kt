package com.mckstudio.countriesapp.layouts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseComposable(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit,
    backClick: (() -> Unit)? = null,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(backClick = backClick)
        },
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}