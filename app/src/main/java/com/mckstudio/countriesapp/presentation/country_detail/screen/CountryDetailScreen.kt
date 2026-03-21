package com.mckstudio.countriesapp.presentation.country_detail.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.components.CABaseScreen
import com.mckstudio.countriesapp.components.CAError
import com.mckstudio.countriesapp.domain.model.BaseTranslation
import com.mckstudio.countriesapp.domain.newmodel.CountryDetail
import com.mckstudio.countriesapp.layouts.LoadingCardView
import com.mckstudio.countriesapp.presentation.country_detail.vm.CountryDetailViewModel
import com.mckstudio.countriesapp.util.createFirstNameToIconMap
import com.mckstuido.countriesapp.R
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import java.util.Locale

@Composable
fun CountryDetailScreen(
    backClick: () -> Unit,
    //onFavoriteClick: () -> Unit,
    countryDetailViewModel: CountryDetailViewModel,
) {
    val detailState by countryDetailViewModel.state.collectAsStateWithLifecycle()
    var expandedTranslation by remember { mutableStateOf(false) }

    CABaseScreen(
        backClick = backClick,
        title = detailState.data?.name ?: "",
        isFavorite = detailState.isFavorite,
        onFavoriteClick = {
            countryDetailViewModel.toggleFavorite()
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                if (detailState.isLoading) {
                    LoadingCardView(modifier = Modifier.align(Alignment.Center))
                }

                if (detailState.error != null) {
                    CAError { /* retry logic */ }
                }

                detailState.data?.let { country ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(Dimens.dp16)
                    ) {
                        CountryImageSection(
                            countryItem = country,
                        )

                        Spacer(modifier = Modifier.height(Dimens.dp24))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = CenterVertically
                        ) {
                            Text(
                                text = "Key Statistics",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Updated 2026",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(Dimens.dp16))

                        StatisticGrid(
                            currency = country.currency,
                            officialName = country.officialName,
                            population = country.population,
                            region = country.region,
                            subregion = country.subregion,
                            status = country.status,
                        )

                        Spacer(modifier = Modifier.height(Dimens.dp16))

                        TranslationsSection(
                            translations = country.translations,
                            isExpanded = expandedTranslation,
                            onExpandClick = { expandedTranslation = !expandedTranslation }
                        )

                        Spacer(modifier = Modifier.height(Dimens.dp16))
                    }
                }
            }
        }
    )
}

@Composable
fun TranslationsSection(
    translations: List<BaseTranslation>,
    isExpanded: Boolean,
    onExpandClick: () -> Unit
) {
    val translationList = remember(translations) {
        translations.filter { it.common != null || it.official != null }
    }

    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ),
        label = "IconRotationAnimation"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onExpandClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = "Other Language Names",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown, // Hep aşağı oku kullanıyoruz
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier
                    .rotate(rotationAngle)
            )
        }

        Spacer(modifier = Modifier.height(Dimens.dp16))

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                translationList.forEach { translation ->
                    val firstName = translation.firstName ?: ""
                    val official = translation.official ?: (translation.common ?: "")
                    val iconResId = createFirstNameToIconMap(firstName)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.width(45.dp),
                                text = firstName.uppercase(Locale.ROOT),
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primaryContainer,
                                fontWeight = FontWeight.Bold
                            )

                            // Çeviri Metni
                            Text(
                                modifier = Modifier.weight(1f),
                                text = official,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Image(
                                painter = painterResource(id = iconResId),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp, 16.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticGrid(
    currency: String,
    officialName: String,
    population: String,
    region: String,
    subregion: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.dp12),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.dp12)) {
            StatCard(
                modifier = Modifier.weight(1f),
                icon = R.drawable.icons_currency,
                label = "Currency",
                value = currency
            )
            StatCard(
                modifier = Modifier.weight(1f),
                icon = R.drawable.icon_official,
                label = "Official Name",
                value = officialName,
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.dp12)) {
            StatCard(
                modifier = Modifier.weight(1f),
                icon = R.drawable.icons_population,
                label = "Population",
                value = population,
            )
            StatCard(
                modifier = Modifier.weight(1f),
                icon = R.drawable.icons_region,
                label = "Region",
                value = region,
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.dp12)) {
            StatCard(
                modifier = Modifier.weight(1f),
                icon = R.drawable.icons_location,
                label = "Sub Region",
                value = subregion,
            )

            StatCard(
                modifier = Modifier.weight(1f),
                icon = R.drawable.icons_status,
                label = "Status",
                value = status,
            )
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    icon: Int,
    label: String,
    value: String
) {
    Card(
        modifier = modifier.heightIn(100.dp),
        shape = RoundedCornerShape(Dimens.dp12),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(Dimens.dp12),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(Dimens.dp32)
                    .clip(CircleShape)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.dp8))
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.height(Dimens.dp4))
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountryImageSection(
    countryItem: CountryDetail,
    modifier: Modifier = Modifier,
) {
    val countryImageList = remember(countryItem) {
        listOfNotNull(
            countryItem.flagUrl,
            countryItem.coatOfArmsUrl,
        )
    }

    val pagerState = rememberPagerState(pageCount = { countryImageList.size })

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(Dimens.dp24))
    ) {
        // 1. Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            key = { index -> countryImageList[index].hashCode() }
        ) { index ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(countryImageList[index])
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = if (index == 0) ContentScale.Crop else ContentScale.Fit
            )
        }

        // 2. Alt Karartma (Overlay Scrim)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 400f
                    )
                )
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(Dimens.dp20),
            verticalAlignment = CenterVertically
        ) {
            Card(
                modifier = Modifier.size(Dimens.dp56, Dimens.dp44),
                shape = RoundedCornerShape(Dimens.dp8),
                elevation = CardDefaults.cardElevation(Dimens.dp4)
            ) {
                AsyncImage(
                    model = countryItem.flagUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(Dimens.dp12))

            Column {
                Text(
                    text = countryItem.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = countryItem.capital.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
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
        AndroidView(
            modifier = Modifier
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
