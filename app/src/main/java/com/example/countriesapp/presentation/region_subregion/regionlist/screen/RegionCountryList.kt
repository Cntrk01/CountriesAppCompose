package com.example.countriesapp.presentation.region_subregion.regionlist.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countriesapp.R
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.CountryDataList
import com.example.countriesapp.layouts.LoadingCardView
import com.example.countriesapp.presentation.region_subregion.regionlist.viewmodel.RegionCountryListViewModel

@Composable
fun RegionCountryList(
    regionCountryListViewModel: RegionCountryListViewModel = hiltViewModel(),
    backClick : ()->Unit,
    clickCountry : (CountryDetailItem)->Unit
){
    val state by regionCountryListViewModel.countryListState.collectAsState()

    Column {
        AppBar(backButtonCheck = true,
            imageId = R.drawable.icons_turkey,
            backClick = {
                backClick.invoke()
                regionCountryListViewModel.resetState()
            })

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.loading) {
                LoadingCardView(modifier = Modifier.align(Alignment.Center))
            }

            if (state.error.isNotBlank()) {
                Text(text = state.error)
            }

            if (state.countryData.isNotEmpty()) {
                CountryDataList(
                    loadListSize = 238,
                    countryList = state.countryData,
                    countryStateListSize = state.countryData.size,
                    stateLoading = state.loading,
                    loadMore = {
                        //regionCountryListViewModel.getCountryList()
                    },
                    clickCountry = { countryDetail ->
                        clickCountry.invoke(countryDetail)
                    })
            }
        }
    }
}