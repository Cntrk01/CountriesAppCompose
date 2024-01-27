package com.example.countriesapp.presentation.country_list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.countriesapp.R
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.CountryDataList
import com.example.countriesapp.layouts.LoadingCardView
import com.example.countriesapp.presentation.country_list.state.CountryListState
import com.example.countriesapp.presentation.country_list.viewmodel.CountryListViewModel

@Composable
fun CountryListScreen(
    countryListViewModel: CountryListViewModel = hiltViewModel(),
    clickCountry: ((CountryDetailItem) -> Unit)? = null,
    backClick: (() -> Unit)? = null
) {
    val state by countryListViewModel.countryListState.collectAsState()

    val checkLoadingSituation = remember {
        mutableStateOf(false)
    }

    Column {
        AppBar(backButtonCheck = true,
            imageId = R.drawable.icons_turkey,
            backClick = {
                backClick?.invoke()
            })

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.loading) {
                checkLoadingSituation.value = true
                LoadingCardView(modifier = Modifier.align(Center))
            } else {
                checkLoadingSituation.value = false
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
                        countryListViewModel.getCountryList()
                    },
                    clickCountry = { countryDetail ->
                        clickCountry?.invoke(countryDetail)
                    })
            }
            //NOT !!! ELSE ifadesini kullandığım için her seferinde aşağı kaydırıp yeni data gelince en başa atıyordu.
            //Fakat if bloklarına cevirince bu düzeldi en sonra gelince en sondan dataları göstermeye devam ediyor <3
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

