package com.mckstudio.countriesapp.presentation.country_list.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mckstudio.countriesapp.components.CABaseScreen
import com.mckstudio.countriesapp.components.CACountryListCard
import com.mckstudio.countriesapp.components.CAError
import com.mckstudio.countriesapp.components.CALoading
import com.mckstudio.countriesapp.presentation.country_list.viewmodel.CountryListViewModel

//Tüm ülkelerin listelendiği sayfa
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    modifier: Modifier = Modifier,
    clickCountry: ((String) -> Unit)? = null,
    backClick: (() -> Unit)? = null,
    countryListViewModel: CountryListViewModel = hiltViewModel()
) {
    val state by countryListViewModel.countryListState.collectAsStateWithLifecycle()

    CABaseScreen(
        modifier = modifier,
        title = "All Country",
        backClick = {
            backClick?.invoke()
        },
        content = {
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
                CACountryListCard(
                    countries = state.countryData,
                    onCountrySelected = { countryName ->
                        clickCountry?.invoke(countryName)
                    }
                )
            }
        },
    )
}

