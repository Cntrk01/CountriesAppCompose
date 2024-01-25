package com.example.countriesapp.presentation.country_detail.screen

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.countriesapp.data.response.toList
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.navigation.Screen
import kotlinx.coroutines.launch

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
                countryItem?.let { CountryImage(countryItem = it) }

                TextRow(
                    firstText = "Country Name",
                    secondText = countryItem?.name?.common.toString(),
                    padding = 20.dp
                )

                countryItem?.currencies?.let { currencies ->
                    currencies.forEach { (currencyCode, currencyData) ->
                        TextRow(
                            firstText = "Currency Code: $currencyCode",
                            secondText = "Name: ${currencyData.name}, Symbol: ${currencyData.symbol}"
                        )
                    }
                }

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

                TextRow(firstText = "SubRegion", secondText = countryItem?.subregion ?: "Unknown")

                countryItem?.timezones?.let { timeZones ->
                    TextRow(
                        firstText = "Time Zones",
                        secondText = timeZones.joinToString(", ")
                    )
                }

                TextRow(firstText = "Translations ", secondText = "")

                countryItem?.translations?.let {
                    Translations(
                        translationList = it.toList()
                    )
                }

                countryItem?.let {
                    OpenGoogleMaps(countryItem = it)
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun OpenGoogleMaps(countryItem: CountryDetailItem){
    Column (
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth(),
    ){
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled=true
                    settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                    webViewClient= WebViewClient()
                    countryItem.maps?.googleMaps?.let { loadUrl(it) }
                }
            },
            update = {webView->
                countryItem?.maps?.googleMaps?.let { webView.loadUrl(it) }
            }
        )
    }
}

@Composable
private fun TextRow(
    firstText: String,
    secondText: String,
    modifier: Modifier = Modifier,
    padding: Dp = 15.dp
) {
    Row(
        modifier = Modifier.padding(top = padding)
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
private fun Translations(
    translationList: List<BaseTranslation>
) {
    Column {
        //step 2 ile kaçar arttıracağımızı yazıyoruz
        for (i in translationList.indices) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                translationList.let {
                    TextRow(
                        firstText = it[i].firstName?.capitalize(Locale.ROOT) ?: "Unknown",
                        secondText = it[i].official ?: "Unknown"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CountryImage(countryItem: CountryDetailItem) {
    val countryImageList = listOf(
        countryItem.flags?.png,
        countryItem.coatOfArms?.png ?: countryItem.coatOfArms?.svg
    )
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        countryImageList.size
    })

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            beyondBoundsPageCount = countryImageList.size,
            state = pagerState,
            key = { it }
        ) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = countryImageList[index]),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(
                            if (index > 0) {
                                10.dp
                            } else {
                                0.dp
                            }
                        ),
                    contentDescription = "Image",
                    contentScale =
                    if (index > 0) { ContentScale.FillHeight }
                    else { ContentScale.Crop }
                )
            }
        }
        Box(
            modifier = Modifier
                .offset(y = 20.dp)
                .fillMaxWidth(0.25f)
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(100))
                .background(Color.LightGray)
                .zIndex(1f)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage - 1
                        )
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Forward"
                )
            }
        }
    }
}


