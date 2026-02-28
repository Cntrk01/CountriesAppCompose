package com.mckstudio.countriesapp.presentation.search.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.CountryDataList
import com.mckstudio.countriesapp.layouts.LoadingCardView
import com.mckstudio.countriesapp.layouts.SearchBar
import com.mckstudio.countriesapp.layouts.ShowForEmptyResult
import com.mckstudio.countriesapp.presentation.search.viewmodel.SearchViewModel
import com.mckstuido.countriesapp.R

@Composable
fun SearchPage(
    backClick: () -> Unit,
    countryDetailItem : (CountryDetailItem)->Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state by searchViewModel.state.collectAsState()
    //detay sayfasına gidip gelince query silinmiş oluyordu.Bunun için bundle görevi gören rememberSaveable kullandım.
    var query by rememberSaveable { mutableStateOf("") }

    Column {
        AppBar(
            imageId = R.drawable.icon_app_bar,
            backClick = backClick
        )

        SearchBar(
            modifier = Modifier.padding(16.dp),
            hint = stringResource(R.string.search_country),
            onSearch = {
                query = it
                searchViewModel.searchCountry(countryName = it)
            },
            lastQuery = query
        )
       Box(modifier = Modifier.fillMaxSize()){
           if (query != "") {
               if (state.loading) {
                   Box(modifier = Modifier.align(Center)){
                       LoadingCardView()
                   }
               } else if (state.error.isNotEmpty()) {
                   Text(
                       modifier = Modifier.align(Center),
                       text = state.error,
                       textAlign = TextAlign.Center
                   )
               } else if (state.searchCountry?.isNotEmpty() == false) {
                   ShowForEmptyResult(
                       imageId = R.drawable.icons_empty,
                       textBelowThePicture = stringResource(R.string.no_country_found_as_a_result_of_the_search),
                   )
               } else if (state.searchCountry?.isNotEmpty() == true) {
                   CountryDataList(
                       countryList = state.searchCountry!!,
                       countryStateListSize = state.searchCountry!!.size,
                       clickCountry = {
                           countryDetailItem.invoke(it)
                       }
                   )
               }
           } else {
               searchViewModel.resetState()
               ShowForEmptyResult(
                   imageId = R.drawable.icons_empty,
                   textBelowThePicture = stringResource(R.string.query_is_empty)
               )
           }
       }

    }
}