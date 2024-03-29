package com.mckstudio.countriesapp.presentation.country_detail.screen

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.mckstudio.countriesapp.data.response.toList
import com.mckstudio.countriesapp.domain.model.BaseTranslation
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.LoadingCardView
import com.mckstudio.countriesapp.navigation.Screen
import com.mckstudio.countriesapp.presentation.favorite.viewmodel.FavoriteViewModel
import com.mckstudio.countriesapp.util.createFirstNameToIconMap
import com.mckstuido.countriesapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import java.util.Locale

@Composable
fun CountryDetailPage(
    navController: NavHostController,
    backClick: () -> Unit,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
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
            backClick = {
                backClick.invoke()
                favoriteViewModel.resetState()
                countryItem = null
            }, backButtonCheck = true,
            imageId = R.drawable.icon_app_bar
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
                        CountryImage(
                            favoriteViewModel = favoriteViewModel,
                            countryItem = it,
                            )

                        ItemRowDesign(
                            icon = R.drawable.icon_capital,
                            firstText = it.name?.common.toString(),
                            secondText = stringResource(id = R.string.country)
                        )

                        it.currencies?.let { currencies ->
                            currencies.forEach { (currencyCode, currencyData) ->
                                ItemRowDesign(
                                    icon = R.drawable.icons_currency,
                                    firstText = "${currencyData.name} (${currencyData.symbol ?: ""})",
                                    secondText = stringResource(R.string.currency)
                                )
                            }
                        }

                        ItemRowDesign(
                            icon = R.drawable.icon_official,
                            firstText = countryItem?.name?.official.toString(),
                            secondText = stringResource(R.string.official_name)
                        )

                        ItemRowDesign(
                            icon = R.drawable.icons_population,
                            firstText = countryItem?.population.toString(),
                            secondText = stringResource(R.string.population)
                        )

                        ItemRowDesign(
                            icon = R.drawable.icons_region,
                            firstText = countryItem?.region.toString(),
                            secondText = stringResource(R.string.region)
                        )

                        ItemRowDesign(
                            icon = R.drawable.icons_status,
                            firstText = countryItem?.status?.capitalize(Locale.ROOT).toString(),
                            secondText = stringResource(R.string.status)
                        )

                        ItemRowDesign(
                            icon = R.drawable.icons_region,
                            firstText = countryItem?.subregion ?: stringResource(R.string.unknown),
                            secondText = stringResource(R.string.sub_region)
                        )

                        countryItem?.timezones?.let { timeZones ->
                            ItemRowDesign(
                                icon = R.drawable.icons_timezone,
                                firstText = timeZones.joinToString(", "),
                                secondText = stringResource(R.string.time_zones)
                            )
                        }

                        ItemRowDesign(
                            icon = R.drawable.icons_translation,
                            firstText = stringResource(R.string.other_language_names),
                            secondText = stringResource(R.string.translations),
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
                                contentDescription = stringResource(R.string.clear),
                                tint = Color.Black,
                                modifier = Modifier.align(CenterHorizontally)
                            )
                        }


                        ItemRowDesign(
                            icon = R.drawable.icons_location,
                            firstText = stringResource(R.string.map),
                            secondText = stringResource(R.string.location)
                        )
                        //AnimatedVisibility(modifier = Modifier.width(350.dp), visible = expandedMap) {
                        //                            Column {
                        //                                countryItem?.let { detail ->
                        //                                    //OpenGoogleMaps(latidude = detail.latlng?.get(0) ?: 0.0, longitude = detail.latlng?.get(1) ?: 0.0)
                        //                                }
                        //                            }
                        //                        }
                        //                        Column(
                        //                            modifier = Modifier
                        //                                .fillMaxWidth()
                        //                                .clickable {
                        //                                    expandedMap = !expandedMap
                        //                                }) {
                        //                            Icon(
                        //                                imageVector = if (expandedMap) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        //                                contentDescription = "Clear",
                        //                                tint = Color.Black,
                        //                                modifier = Modifier.align(CenterHorizontally)
                        //                            )
                        //                        }
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
            contentDescription = stringResource(R.string.icon),
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
private fun OpenGoogleMaps(latidude: Double, longitude: Double) {
    Column(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
    ) {
        val currenctContext = LocalContext.current
        AndroidView(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
            factory = { _ ->
                MapView(currenctContext).apply {
                    setTileSource(TileSourceFactory.CLOUDMADESMALLTILES)
                    this.setTileSource(TileSourceFactory.MAPNIK)
                    //this.setBuiltInZoomControls(true)
                    //this.setMultiTouchControls(true)
                    setOnClickListener {
                    }
                }
            },
            update = { view ->
                view.controller.setZoom(5.0)
                view.controller.setCenter(
                    GeoPoint(
                        latidude,
                        longitude
                    )
                )
            })
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                translationList[i].official?.let { official ->
                    translationList[i].firstName?.let { firstName ->
                        val iconResId = createFirstNameToIconMap(firstName)

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
private fun CountryImage(
    favoriteViewModel: FavoriteViewModel,
    countryItem: CountryDetailItem,
) {
    val countryImageList = listOf(
        countryItem.flags?.png,
        countryItem.coatOfArms?.png ?: countryItem.coatOfArms?.svg
    )
    var checkExistsDb by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        countryImageList.size
    })
    countryItem.name?.let { favoriteViewModel.checkExistCountry(it) }

    val favoriteState by favoriteViewModel.state.collectAsState()

    favoriteState.checkExists?.let {
        checkExistsDb = if (it > 0) {
            1
        } else {
            0
        }
    }

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
                    contentDescription = stringResource(id = R.string.image),
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
                .zIndex(1f)
                .width(50.dp)
                .height(50.dp)
                .align(TopEnd)
                .padding(top = 10.dp, end = 10.dp)
                .background(Color.Transparent)
                .clickable {
                    if (checkExistsDb == 1) {
                        favoriteViewModel.deleteCountry(countryItem)
                    } else {
                        favoriteViewModel.addCountry(countryItem)
                    }
                }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
                    ,
                painter = if (checkExistsDb == 1) {
                    painterResource(id = R.drawable.icons_star)
                } else {
                    painterResource(id = R.drawable.icons_un_star)
                },
                contentDescription = ""
            )
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
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = stringResource(R.string.back)
                )
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
                    contentDescription = stringResource(R.string.forward)
                )
            }
        }
    }
}


