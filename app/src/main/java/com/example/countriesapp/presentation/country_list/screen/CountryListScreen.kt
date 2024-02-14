package com.example.countriesapp.presentation.country_list.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countriesapp.R
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.CountryDataList
import com.example.countriesapp.layouts.ErrorText
import com.example.countriesapp.layouts.LoadingCardView
import com.example.countriesapp.presentation.country_list.viewmodel.CountryListViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CountryListScreen(
    countryListViewModel: CountryListViewModel = hiltViewModel(),
    clickCountry: ((CountryDetailItem) -> Unit)? = null,
    backClick: (() -> Unit)? = null
) {
    //val state = countryListViewModel.countryListState.value
    val state by countryListViewModel.countryListState.collectAsState()
    //var checkError by remember { mutableStateOf(false) }

    Column {
        AppBar(backButtonCheck = true,
            imageId = R.drawable.icon_app_bar,
            backClick = {
                backClick?.invoke()
                //countryListViewModel.resetState()
            })

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.loading) {
                //checkError = false
                LoadingCardView(modifier = Modifier.align(Center))
            }

            if (state.error.isNotBlank()) {
                //checkError = true
                Box(
                    modifier = Modifier.align(Center),
                    contentAlignment = Center
                ) {
                    ErrorText(
                        errorMessage = state.error,
                        clickRetryButton = {
                            countryListViewModel.getCountryList()
                            //checkError = false
                        })
                }
            }

            if (state.countryData.isNotEmpty()) {
                CountryDataList(
                    loadListSize = 238,
                    countryList =state.countryData,
                    countryStateListSize = state.countryData.size,
                    stateLoading = state.loading,
                    loadMore = {
                        countryListViewModel.getCountryList()
                    },
                    clickCountry = { countryDetail ->
                        clickCountry?.invoke(countryDetail)
                    })
            }

            //NOT !!! ELSE ifadesini kullandığım için her seferinde aşağı kaydırıp yeni data gelince en başa atıyordu.
            //Fakat if bloklarına cevirince bu düzeldi en sonra gelince en sondan dataları göstermeye devam ediyor <3
            //else if kullandıgımda ekran recompositiona ugruyor ve yeniden çiziliyor !!!
            //  if (state.loading) {
            //            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            //        } else if (state.error.isNotBlank()) {
            //            Text(text = state.error)
            //        } else {
            //            CountryDataList(
            //                countryListViewModel = countryListViewModel,
            //                state = state
            //            )
            //        }
        }
    }
}

