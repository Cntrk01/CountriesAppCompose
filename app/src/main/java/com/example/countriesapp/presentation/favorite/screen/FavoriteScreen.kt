package com.example.countriesapp.presentation.favorite.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import com.example.countriesapp.layouts.LoadingCardView
import com.example.countriesapp.presentation.favorite.viewmodel.FavoriteViewModel
import com.example.countriesapp.util.mapToDetailItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    backClick: () -> Unit,
    clickFavoriteItem: (CountryDetailItem) -> Unit,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    favoriteViewModel.getAllCountry()

    val state by favoriteViewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    
    Column {
        AppBar(
            imageId = R.drawable.icon_app_bar,
            backClick = {
                backClick.invoke()
            },
            backButtonCheck = true
        )
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.loading == true) {
                LoadingCardView(modifier = Modifier.align(Alignment.Center))
            }

            if (state.error.isNotBlank()) {
                Text(text = state.error)
            }

            if (state.favoriteList?.isNotEmpty() == true) {
                state.favoriteList?.let {list->
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        contentPadding = PaddingValues(10.dp)
                    ) {

                        items(list.size) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(5.dp)
                                    .clickable {
                                        coroutineScope.launch {
                                            delay(800)
                                            clickFavoriteItem.invoke(list[it])
                                        }
                                    },
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        modifier = Modifier.fillMaxSize(),
                                        model = list[it].flags?.png,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop
                                    )
                                    list[it].name?.common?.let { it1 ->
                                        Text(
                                            modifier = Modifier
                                                .align(Alignment.BottomCenter)
                                                .padding(bottom = 10.dp),
                                            maxLines = 2,
                                            text = it1,
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
            }
        }
    }
}