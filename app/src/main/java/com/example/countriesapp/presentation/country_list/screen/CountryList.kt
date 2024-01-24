package com.example.countriesapp.presentation.country_list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.countriesapp.presentation.country_list.state.CountryListState
import com.example.countriesapp.presentation.country_list.viewmodel.CountryListViewModel

@Composable
fun CountryList(
    countryListViewModel: CountryListViewModel = hiltViewModel()
) {
    val state by countryListViewModel.countryListState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (state.error.isNotBlank()) {
            Text(text = state.error)
        } else {
            CountryDataList(
                countryListViewModel = countryListViewModel,
                state = state
            )
        }
    }

}

@Composable
fun CountryDataList(
    countryListViewModel: CountryListViewModel,
    state: CountryListState
) {
    state.countryData?.let { listCountry ->

        val count = if (listCountry.size % 2 == 0) {
            listCountry.size / 2
        } else {
            listCountry.size / 2 + 1
        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {

            items(count) { countryList ->
                if (countryList >= count - 1 && !state.loading) {
                    countryListViewModel.getCountryList()
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                ) {

                   Box{
                       AsyncImage(
                           modifier = Modifier
                               .fillMaxWidth()
                               .fillMaxHeight(),
                           model = listCountry[countryList].flag?.png,
                           contentDescription = "Image",
                           contentScale = ContentScale.FillHeight
                       )

                       Text(
                           modifier = Modifier
                               .padding(top = 10.dp)
                               .background(Color.Transparent)
                               .align(Alignment.BottomCenter)
                               .padding(10.dp),
                           maxLines = 1,
                           text = listCountry[countryList].name.toString(),
                           fontSize = 22.sp,
                           fontFamily = FontFamily.SansSerif,
                           fontWeight = FontWeight.ExtraBold,
                           textAlign = TextAlign.Center
                       )
                   }

                }

            }

        }
    }
}