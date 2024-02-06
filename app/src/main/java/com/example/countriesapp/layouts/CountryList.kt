package com.example.countriesapp.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.domain.model.CountryItem

@Composable
fun CountryDataList(
    loadListSize: Int,
    countryList : List<CountryItem>,
    countryStateListSize : Int,
    stateLoading : Boolean,
    loadMore: (() -> Unit)? = null,
    clickCountry: ((CountryDetailItem) -> Unit)? = null
) {

    if (countryList.isNotEmpty()) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(
                count = countryStateListSize,
                key = {
                    countryList[it].name.toString()
                },
                itemContent = {countryList1 ->
                    if (countryList1 >= countryStateListSize - 1 && !stateLoading) {
                        if (countryStateListSize < loadListSize) {
                            loadMore?.invoke()
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(5.dp)
                            .clickable {
                                clickCountry?.invoke(countryList[countryList1].countryDetailItem)
                            },
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)

                    ) {
                        Box {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                model = countryList[countryList1].flag?.png,
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
                                text = countryList[countryList1].name.toString(),
                                fontSize = 22.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center,
                                color = Color.DarkGray
                            )
                        }
                    }

                }
            )
        }
    }
}