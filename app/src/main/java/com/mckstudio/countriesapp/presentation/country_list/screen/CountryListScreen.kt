package com.mckstudio.countriesapp.presentation.country_list.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mckstudio.countriesapp.components.CABaseScreen
import com.mckstudio.countriesapp.components.CALoading
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.layouts.CountryDataList
import com.mckstudio.countriesapp.presentation.country_list.viewmodel.CountryListViewModel
import com.mckstudio.countriesapp.components.CAError

//Tüm ülkelerin listelendiği sayfa
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    clickCountry: ((CountryDetailItem) -> Unit)? = null,
    backClick: (() -> Unit)? = null,
    countryListViewModel: CountryListViewModel = hiltViewModel()
) {
    val state by countryListViewModel.countryListState.collectAsStateWithLifecycle()

    CABaseScreen(
        title = "All Country",
        backClick = {
            backClick?.invoke()
        },
        content = { modifier ->
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                if (state.loading) {
                    CALoading(
                        statusText = "Countries are being loaded."
                    )
                }

                if (state.error.isNotBlank()) {
                    CAError {
                        countryListViewModel.getCountryList()
                    }
                }

                if (state.countryData.isNotEmpty()) {
                    CountryDataList(
                        countryList = state.countryData,
                        countryStateListSize = state.countryData.size,
                        clickCountry = { countryDetail ->
                            clickCountry?.invoke(countryDetail)
                        }
                    )
                }
            }
        },
    )
}

