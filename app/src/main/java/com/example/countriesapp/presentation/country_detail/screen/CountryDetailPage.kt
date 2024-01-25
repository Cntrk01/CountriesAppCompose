package com.example.countriesapp.presentation.country_detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.countriesapp.data.response.Translations
import com.example.countriesapp.data.response.toList
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.navigation.Screen
import com.google.android.gms.maps.MapView
import java.util.ArrayList
import java.util.Locale

@Composable
fun CountryDetailPage(
    navController: NavHostController,
    backClick: () -> Unit,
) {
    //remember içinde yapmayınca da oluyor fakat geri tuşuna bastığım an saniyelik datalar null gözüküyordu remember ile çözdüm.
    var countryItem = remember {
        navController.previousBackStackEntry?.savedStateHandle?.get<CountryDetailItem>(Screen.CountryDetailPage.route)
    }

    Column {
        AppBar(countryName = countryItem?.name?.common.toString(), backClick = {
            backClick.invoke()
            countryItem = null
        }, backButtonCheck = true)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = countryItem?.flags?.png),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop
                    )
                }

                TextRow(
                    firstText = "Country Name",
                    secondText = countryItem?.name?.common.toString()
                )

                TextRow(
                    firstText = "Official Name",
                    secondText = countryItem?.name?.official.toString()
                )

                TextRow(firstText = "Population", secondText = countryItem?.population.toString())

                TextRow(firstText = "Region", secondText = countryItem?.region.toString())

                TextRow(
                    firstText = "Status",
                    secondText = countryItem?.status?.capitalize(Locale.ROOT).toString()
                )

                TextRow(firstText = "SubRegion", secondText = countryItem?.subregion.toString())

                countryItem?.timezones?.map {
                    TextRow(firstText = "Time Zone", secondText = it)
                }

                TextRow(firstText = "Translations ", secondText = "")

                countryItem?.translations?.let {
                    Translations(
                        translationList = it.toList()
                    )
                }
            }
        }
    }
}

@Composable
fun TextRow(
    firstText: String,
    secondText: String
) {
    Row(
        modifier = Modifier.padding(top = 15.dp)
    ) {
        Text(
            color = Color.Black,
            text = "$firstText : ",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = secondText,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Translations(
    translationList: List<BaseTranslation>
) {
    Column {
        for (i in translationList.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextRow(
                    firstText = translationList[i].firstName.capitalize(Locale.ROOT),
                    secondText = translationList[i].official
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (i + 1 < translationList.size) {
                    TextRow(
                        firstText = translationList[i + 1].firstName.capitalize(Locale.ROOT),
                        secondText = translationList[i + 1].official
                    )
                }
            }
        }
    }
}


