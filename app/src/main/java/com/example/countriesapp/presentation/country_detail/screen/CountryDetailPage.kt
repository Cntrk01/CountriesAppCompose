package com.example.countriesapp.presentation.country_detail.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.countriesapp.R
import com.example.countriesapp.data.response.toList
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.LoadingCardView
import com.example.countriesapp.navigation.Screen
import com.example.countriesapp.util.CreateFirstNameToIconMap
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.views.overlay.ScaleBarOverlay
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
    var loading by remember { mutableStateOf(true) }

    var expandedTranslation by remember { mutableStateOf(false) }
    var expandedMap by remember { mutableStateOf(false) }

    LaunchedEffect(loading) {
        if (loading) {
            delay(1000)
            loading = false
        }
    }

    Column {
        AppBar(
            //countryName = countryItem?.name?.common.toString(),
            backClick = {
                backClick.invoke()
                countryItem = null
            }, backButtonCheck = true,
            imageId = R.drawable.icons_turkey
        )

        if (loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                LoadingCardView(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
                item {
                    countryItem?.let {
                        CountryImage(countryItem = it)

                        ItemRowDesign(
                            icon = R.drawable.icon_capital,
                            firstText = it.name?.common.toString(),
                            secondText = "Country"
                        )

                        it.currencies?.let { currencies ->
                            currencies.forEach { (currencyCode, currencyData) ->
                                ItemRowDesign(
                                    icon = R.drawable.icons_currency,
                                    firstText = "${currencyData.name} (${currencyData.symbol ?: ""})",
                                    secondText = "Currency"
                                )
                            }
                        }

                        ItemRowDesign(
                            icon = R.drawable.icon_official,
                            firstText = countryItem?.name?.official.toString(),
                            secondText = "Official Name"
                        )

                        ItemRowDesign(
                            icon = R.drawable.icons_population,
                            firstText = countryItem?.population.toString(),
                            secondText = "Population"
                        )

                        ItemRowDesign(
                            icon = R.drawable.icons_region,
                            firstText = countryItem?.region.toString(),
                            secondText = "Region"
                        )

                        ItemRowDesign(
                            icon = R.drawable.icons_status,
                            firstText = countryItem?.status?.capitalize(Locale.ROOT).toString(),
                            secondText = "Status"
                        )

                        ItemRowDesign(
                            icon = R.drawable.icons_region,
                            firstText = countryItem?.subregion ?: "Unknown",
                            secondText = "Sub Region"
                        )

                        countryItem?.timezones?.let { timeZones ->
                            ItemRowDesign(
                                icon = R.drawable.icons_timezone,
                                firstText = timeZones.joinToString(", "),
                                secondText = "Time Zones"
                            )
                        }

                        ItemRowDesign(
                            icon = R.drawable.icons_translation,
                            firstText = "Other Language Names",
                            secondText = "Translations"
                        )

                        AnimatedVisibility(visible = expandedTranslation) {
                            Column {
                                countryItem?.translations?.let { translations ->
                                    Translations(translationList = translations.toList())
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedTranslation = !expandedTranslation
                                }) {
                            Icon(
                                imageVector = if (expandedTranslation) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = "Clear",
                                tint = Color.Black,
                                modifier = Modifier.align(CenterHorizontally)
                            )
                        }


                        ItemRowDesign(
                            icon = R.drawable.icons_location,
                            firstText = "Map",
                            secondText = "Location"
                        )

                        AnimatedVisibility(visible = expandedMap) {
                            Column {
                                countryItem?.let { detail ->
                                    OpenGoogleMaps(countryItem = detail)
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedMap = !expandedMap
                                }) {
                            Icon(
                                imageVector = if (expandedMap) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = "Clear",
                                tint = Color.Black,
                                modifier = Modifier.align(CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemRowDesign(
    icon: Int,
    firstText: String,
    secondText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Icon",
            modifier = Modifier
                .size(45.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .align(CenterVertically)

        ) {
            Text(
                text = firstText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                text = secondText,
                fontSize = 14.sp
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun OpenGoogleMaps(countryItem: CountryDetailItem) {

    Column(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth(),
    ) {
        // MapView'in context'i
        val context = LocalContext.current


        // MapView'i gösterme
        //AndroidView(
        //            factory = { mapView },
        //            modifier = Modifier.fillMaxSize()
        //        ) { map ->
        //            // Map ayarlarını yapma
        //            map.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
        //            map.setMultiTouchControls(true)
        //
        //            // Ölçek çubuğunu ekleme
        //            val scaleBarOverlay = ScaleBarOverlay(map)
        //            map.overlays.add(scaleBarOverlay)
        //
        //            // Belirli bir koordinata odaklanma örneği
        //            val targetLatitude = 40.7128 // Hedef ülkenin enlemi
        //            val targetLongitude = -74.0060 // Hedef ülkenin boylamı
        //            map.controller.setCenter(org.osmdroid.util.GeoPoint(targetLatitude, targetLongitude))
        //            map.controller.setZoom(10.0) // İstediğiniz zoom seviyesini ayarlayabilirsiniz
        //        }

        //AndroidView(
            //modifier = Modifier.fillMaxSize(),
            //            factory = { context ->
            //                WebView(context).apply {
            //                    layoutParams = ViewGroup.LayoutParams(
            //                        ViewGroup.LayoutParams.MATCH_PARENT,
            //                        ViewGroup.LayoutParams.MATCH_PARENT
            //                    )
            //                    settings.javaScriptEnabled = true
            //                    settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            //                    webViewClient = WebViewClient()
            //                    countryItem.maps?.googleMaps?.let { loadUrl(it) }
            //                }
            //            },
            //            update = { webView ->
            //                countryItem?.maps?.googleMaps?.let { webView.loadUrl(it) }
            //            }

        //)

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
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                translationList[i].official?.let { official ->
                    translationList[i].firstName?.let { firstName ->
                        val iconResId = CreateFirstNameToIconMap(firstName)

                        ItemRowDesign(
                                icon = iconResId,
                                firstText = firstName.capitalize(Locale.ROOT),
                                secondText = official
                            )

                    }
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
                    if (index > 0) {
                        ContentScale.FillHeight
                    } else {
                        ContentScale.Crop
                    }
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


