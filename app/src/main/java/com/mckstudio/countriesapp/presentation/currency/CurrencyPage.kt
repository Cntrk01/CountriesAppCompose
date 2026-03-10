package com.mckstudio.countriesapp.presentation.currency

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.components.CABaseScreen
import com.mckstudio.countriesapp.components.CACountryListItem
import com.mckstudio.countriesapp.layouts.ErrorText
import com.mckstudio.countriesapp.layouts.LoadingCardView
import com.mckstudio.countriesapp.presentation.country_list.viewmodel.CountryListViewModel
import com.mckstudio.countriesapp.ui.components.CASearchBar

@Composable
fun CurrencyPage(
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
                    val currencyMatch = country.countryDetailItem.currencies?.values?.any {
                        it.name.contains(searchQuery, ignoreCase = true)
                    } == true
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
        content = { modifier ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(Dimens.dp12),
            ) {
                CASearchBar(
                    searchText = searchQuery,
                    onSearchTextChange = { searchQuery = it },
                    placeholder = "Search country or currency..."
                )

                Spacer(modifier = Modifier.height(Dimens.dp8))

                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    if (state.loading) {
                        LoadingCardView(modifier = Modifier.align(Alignment.Center))
                    }

                    if (state.error.isNotBlank()) {
                        ErrorText(
                            modifier = Modifier.align(Alignment.Center),
                            errorMessage = state.error,
                            clickRetryButton = { currencyViewModel.getCountryList() }
                        )
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
                                        val currencyInfo = country.countryDetailItem.currencies?.values?.firstOrNull()
                                        val subtitleText = currencyInfo?.let { "${it.name} ${it.symbol}" } ?: "N/A"

                                        CACountryListItem(
                                            title = country.name ?: "",
                                            subtitle = subtitleText,
                                            imageUrl = country.flag?.png,
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
        }
    )
}