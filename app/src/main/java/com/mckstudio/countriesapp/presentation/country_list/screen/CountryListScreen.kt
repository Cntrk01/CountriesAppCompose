package com.mckstudio.countriesapp.presentation.country_list.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.BaseComposable
import com.mckstudio.countriesapp.layouts.CountryDataList
import com.mckstudio.countriesapp.layouts.ErrorText
import com.mckstudio.countriesapp.layouts.LoadingCardView
import com.mckstudio.countriesapp.presentation.country_list.viewmodel.CountryListViewModel
import com.mckstuido.countriesapp.R

//Tüm ülkelerin listelendiği sayfa
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    countryListViewModel: CountryListViewModel = hiltViewModel(),
    clickCountry: ((CountryDetailItem) -> Unit)? = null,
    backClick: (() -> Unit)? = null
) {
    val state by countryListViewModel.countryListState.collectAsStateWithLifecycle()

    BaseComposable(
        backClick = {
            backClick?.invoke()
        },
        content = { modifier ->
            Column (
                modifier = modifier,
                verticalArrangement = Arrangement.Center
            ){
                if (state.loading) {
                    //checkError = false
                    LoadingCardView(modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                if (state.error.isNotBlank()) {
                    //checkError = true
                    Box(
                        contentAlignment = Center
                    ) {
                        ErrorText(
                            modifier = Modifier.align(Center),
                            errorMessage = state.error,
                            clickRetryButton = {
                                countryListViewModel.getCountryList()
                                //checkError = false
                            })
                    }
                }

                if (state.countryData.isNotEmpty()) {
                    CountryDataList(
                        countryList = state.countryData,
                        countryStateListSize = state.countryData.size,
                        clickCountry = { countryDetail ->
                            clickCountry?.invoke(countryDetail)
                        })
                }

                //NOT !!! ELSE ifadesini kullandığım için her seferinde aşağı kaydırıp yeni data gelince en başa atıyordu.
                //Fakat if bloklarına cevirince bu düzeldi en sonra gelince en sondan dataları göstermeye devam ediyor <3
                //else if kullandıgımda ekran recompositiona ugruyor ve yeniden çiziliyor !!!

                //Çözümü :
                //else if → mutually exclusive: sadece bir blok çalışır, diğerleri yok olur → recomposition → liste sıfırlanabilir
                //Bağımsız if → bir blok değişince diğerleri etkilenmez → scroll korunur

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
        },
    )
}

