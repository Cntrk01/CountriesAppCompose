package com.mckstudio.countriesapp.presentation.currency

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.components.CABaseScreen
import com.mckstudio.countriesapp.components.CAError
import com.mckstudio.countriesapp.components.CALoading
import com.mckstudio.countriesapp.components.CASearchBar
import com.mckstudio.countriesapp.components.CASearchItem
import com.mckstudio.countriesapp.presentation.country_list.viewmodel.CountryListViewModel

@Composable
fun CurrencyPage(
    modifier: Modifier = Modifier,
    backClick: (() -> Unit)? = null,
    currencyViewModel: CountryListViewModel = hiltViewModel()
) {
    val state by currencyViewModel.countryListState.collectAsState()

    val mainListState = rememberLazyListState()
    val searchListState = rememberLazyListState()

    var searchQuery by remember { mutableStateOf("") }

    // Filtrelenmiş liste (derivedStateOf kullanarak gereksiz hesaplamaları önlüyoruz)
    val filteredCountries by remember(searchQuery, state.countryData) {
        derivedStateOf {
            if (searchQuery.isEmpty()) {
                state.countryData
            } else {
                state.countryData.filter { country ->
                    val countryNameMatch = country.name?.contains(searchQuery, ignoreCase = true) == true
                    val currencyMatch = country.currency?.contains(searchQuery, ignoreCase = true) == true
                    countryNameMatch || currencyMatch
                }
            }
        }
    }

    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            searchListState.scrollToItem(0)
        }
    }

    val activeListState by remember(searchQuery) {
        derivedStateOf {
            if (searchQuery.isEmpty()) mainListState else searchListState
        }
    }

    CABaseScreen(
        title = "Currency",
        backClick = { backClick?.invoke() },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(Dimens.dp12),
            ) {
                if (!state.loading){
                    CASearchBar(
                        searchText = searchQuery,
                        onSearchTextChange = { searchQuery = it },
                        placeholder = "Search currency"
                    )

                    Spacer(modifier = Modifier.height(Dimens.dp8))
                }

                if (state.loading) {
                    CALoading(
                        modifier = Modifier.fillMaxSize(),
                        statusText = "Currencies are being loaded."
                    )
                }

                if (state.error.isNotBlank()) {
                    CAError (
                        title = state.error,
                        description = "Something went wrong",
                        retryButtonText = "Try Again"
                    ){
                        currencyViewModel.getCountryList()
                    }
                }

                if (!state.loading && state.error.isBlank()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(Dimens.dp8),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.dp2)
                    ) {
                        if (filteredCountries.isEmpty() && searchQuery.isNotEmpty()) {
                            Text(
                                text = "No results found for '$searchQuery'",
                                modifier = Modifier
                                    .padding(Dimens.dp16)
                                    .align(Alignment.CenterHorizontally),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        } else {
                            LazyColumn (
                                state = activeListState,
                            ){
                                items(
                                    count = filteredCountries.size,
                                ) { index ->
                                    val country = filteredCountries[index]

                                    CASearchItem(
                                        title = country.name ?: "",
                                        subtitle = country.currency.toString(),
                                        imageUrl = country.flag,
                                    )

                                    if (index < filteredCountries.size - 1) {
                                        HorizontalDivider(
                                            modifier = Modifier.padding(horizontal = Dimens.dp16),
                                            thickness = 0.5.dp,
                                            color = MaterialTheme.colorScheme.outlineVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}