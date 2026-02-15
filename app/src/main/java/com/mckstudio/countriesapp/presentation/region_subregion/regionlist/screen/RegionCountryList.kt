package com.mckstudio.countriesapp.presentation.region_subregion.regionlist.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.CountryDataList
import com.mckstudio.countriesapp.layouts.ErrorText
import com.mckstudio.countriesapp.layouts.LoadingCardView
import com.mckstudio.countriesapp.presentation.region_subregion.regionlist.viewmodel.RegionCountryListViewModel
import com.mckstuido.countriesapp.R

@Composable
fun RegionCountryList(
    regionCountryListViewModel: RegionCountryListViewModel = hiltViewModel(),
    backClick : ()->Unit,
    clickCountry : (CountryDetailItem)->Unit
){
    val state by regionCountryListViewModel.countryListState.collectAsState()

    Column {
        AppBar(backButtonCheck = true,
            imageId = R.drawable.icon_app_bar,
            backClick = {
                backClick.invoke()
                regionCountryListViewModel.resetState()
            })

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.loading) {
                LoadingCardView(modifier = Modifier.align(Alignment.Center))
            }

            if (state.error.isNotBlank()) {
                Box(
                    modifier = Modifier.align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorText(
                        errorMessage = state.error,
                        clickRetryButton = {
                            regionCountryListViewModel.getCountryList()
                        })
                }
            }

            if (state.countryData.isNotEmpty()) {
                CountryDataList(
                    countryList = state.countryData,
                    countryStateListSize = state.countryData.size,
                    clickCountry = { countryDetail ->
                        clickCountry.invoke(countryDetail)
                    })
            }
        }
    }
}