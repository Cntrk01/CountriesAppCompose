package com.mckstudio.countriesapp.presentation.favorite.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mckstudio.countriesapp.components.CABaseScreen
import com.mckstudio.countriesapp.components.CACountryListCard
import com.mckstudio.countriesapp.layouts.LoadingCardView
import com.mckstudio.countriesapp.layouts.ShowForEmptyResult
import com.mckstudio.countriesapp.presentation.favorite.viewmodel.FavoriteViewModel
import com.mckstuido.countriesapp.R

@Composable
fun FavoriteScreen(
    backClick: () -> Unit,
    onClickFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by favoriteViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        favoriteViewModel.getAllCountry()
    }

    CABaseScreen(
        title = "Favorite Countries",
        backClick = {
            backClick.invoke()
            favoriteViewModel.resetState()
        },
        content = {
            Box(modifier = modifier.fillMaxSize()) {
                if (state.loading == true) {
                    LoadingCardView(modifier = Modifier.align(Alignment.Center))
                }

                if (state.error.isNotBlank()) {
                    Text(text = state.error)
                }

                if (state.favoriteList?.isNotEmpty() == true) {
                    CACountryListCard(
                        countries = state.favoriteList!!,
                        onCountrySelected = {
                            onClickFavorite(it)
                        }
                    )
                }

                if (state.favoriteList?.isNotEmpty() == false && state.loading == false && state.error.isBlank()) {
                    ShowForEmptyResult(
                        imageId = R.drawable.icons_empty,
                        textBelowThePicture = stringResource(R.string.no_countries_are_favorites))
                }
            }
        }
    )
}