package com.mckstudio.countriesapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CABaseScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    title: String = "Country App",
    backClick: (() -> Unit)? = null,
    isFavorite : Boolean = false,
    onFavoriteClick : (() -> Unit) ?= null,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CATopBar(
                title = title,
                onBackClick = backClick,
                isFavorite = isFavorite,
                onFavoriteClick = onFavoriteClick,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            content()
        }
    }
}