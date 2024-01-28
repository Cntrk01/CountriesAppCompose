package com.example.countriesapp.presentation.currency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.countriesapp.R
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HorizontalLine
import com.example.countriesapp.layouts.LoadingCardView
import com.example.countriesapp.presentation.country_list.viewmodel.CountryListViewModel

@Composable
fun CurrencyPage(
    backClick: (() -> Unit)? = null,
    currencyViewModel: CountryListViewModel = hiltViewModel()
) {
    val state by currencyViewModel.countryListState.collectAsState()

    Column {
        AppBar(backButtonCheck = true,
            imageId = R.drawable.icons_turkey,
            backClick = {
                backClick?.invoke()
            })

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.loading) {
                LoadingCardView(modifier = Modifier.align(Alignment.Center))
            }

            if (state.error.isNotBlank()) {
                Text(text = state.error)
            }

            if (state.countryData.isNotEmpty()) {
                Column {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp).padding(start = 25.dp),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Flag",
                                textAlign = TextAlign.Center)
                        }
                        Column(
                            modifier = Modifier.weight(1f).padding(start = 15.dp)
                        ) {
                            Text(text = "Country",
                                textAlign = TextAlign.Center)
                        }
                        Column(
                            modifier = Modifier.weight(1f).padding(start = 10.dp)
                        ) {
                            Text(text = "Currencies",
                                textAlign = TextAlign.Center)
                        }

                    }

                    HorizontalLine(heightDp = 1.dp)

                    LazyColumn(
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(state.countryData.size) { countryList ->
                            if (countryList >= state.countryData.size - 1 && !state.loading) {
                                if (state.countryData.size < 238) {
                                    currencyViewModel.getCountryList()
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp),
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(0.3f)
                                        .align(CenterVertically)
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(),
                                        model = state.countryData[countryList].flag?.png,
                                        contentDescription = "Image",
                                        contentScale = ContentScale.FillHeight
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(0.7f)
                                        .align(CenterVertically)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(),
                                        text = state.countryData[countryList].name.toString(),
                                        textAlign = TextAlign.Center,
                                        maxLines = 2
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .weight(0.3f)
                                        .align(CenterVertically)
                                ) {
                                    state.countryData[countryList].countryDetailItem.currencies?.let {currency->
                                        currency.forEach {(currencyCode, currencyData) ->
                                            Text(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .fillMaxHeight(),
                                                text = "(${currencyData.name}+ ${currencyData.symbol})",
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier
                                .height(10.dp)
                            )

                        }
                    }

                }
            }
        }
    }
}