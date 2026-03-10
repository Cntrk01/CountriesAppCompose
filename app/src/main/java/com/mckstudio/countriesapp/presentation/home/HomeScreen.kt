package com.mckstudio.countriesapp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mckstudio.countriesapp.common.Constants.Country_Title
import com.mckstudio.countriesapp.common.Constants.Country_Subtitle
import com.mckstudio.countriesapp.common.Constants.Currency_Subtitle
import com.mckstudio.countriesapp.common.Constants.Currency_Title
import com.mckstudio.countriesapp.common.Constants.Favorite_Subtitle
import com.mckstudio.countriesapp.common.Constants.Favorite_Title
import com.mckstudio.countriesapp.common.Constants.Quiz_Subtitle
import com.mckstudio.countriesapp.common.Constants.Quiz_Title
import com.mckstudio.countriesapp.common.Constants.Region_Subtitle
import com.mckstudio.countriesapp.common.Constants.Region_Title
import com.mckstudio.countriesapp.common.Constants.Sub_Region_Subtitle
import com.mckstudio.countriesapp.common.Constants.Sub_Region_Title
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.components.CABaseScreen
import com.mckstudio.countriesapp.components.CARecommendedCard
import com.mckstudio.countriesapp.components.RecommendedCardModel
import com.mckstudio.countriesapp.ui.components.CACategoryCard
import com.mckstudio.countriesapp.ui.components.CASearchBar
import com.mckstudio.countriesapp.ui.components.CategoryCardModel
import com.mckstudio.countriesapp.ui.theme.CABlue
import com.mckstudio.countriesapp.ui.theme.CountriesAppTheme
import com.mckstudio.countriesapp.ui.theme.SoftGreen
import com.mckstudio.countriesapp.ui.theme.SoftOrange
import com.mckstudio.countriesapp.ui.theme.SoftPink
import com.mckstudio.countriesapp.ui.theme.SoftPurple
import com.mckstudio.countriesapp.ui.theme.SoftRed
import com.mckstuido.countriesapp.R

@Composable
fun HomeScreen(
    clickHomeItem: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val recommendedDummyList = listOf(
        RecommendedCardModel("Switzerland", "Bern", "Europe", "https://example.com/1.jpg", {}),
        RecommendedCardModel("Maldives", "Male", "Asia", "https://example.com/2.jpg", {}),
        RecommendedCardModel("France", "Paris", "Europe", "https://example.com/3.jpg", {})
    )

    CABaseScreen(
        title = "Country App",
        backClick = null,
        content = { modifier ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(Dimens.dp12),
                contentPadding = PaddingValues(bottom = Dimens.dp12),
                verticalArrangement = Arrangement.spacedBy(Dimens.dp16)
            ) {
                item {
                    CASearchBar(
                        searchText = searchQuery,
                        onSearchTextChange = { searchQuery = it }
                    )
                }

                item {
                    Text(
                        text = "Explore World",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                item {
                    CategoryGrid(
                        onSelectedCategory = { clickHomeItem(it) }
                    )
                }

                item {
                    Text(
                        text = "Recommended For You",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }

                item {
                    RecommendedGrid(
                        recommendedList = recommendedDummyList,
                        onSelectedRecommended = { clickHomeItem(it) }
                    )
                }
            }
        }
    )

}

@Composable
fun CategoryGrid(
    modifier: Modifier = Modifier,
    onSelectedCategory: (String) -> Unit = {}
) {
    val categoryList = listOf(
        CategoryCardModel(
            title = Country_Title,
            subtitle = Country_Subtitle,
            iconRes = R.drawable.ic_globe,
            iconBackgroundColor = CABlue,
        ),
        CategoryCardModel(
            title = Region_Title,
            subtitle = Region_Subtitle,
            iconRes = R.drawable.ic_regions,
            iconBackgroundColor = SoftPurple,
        ),
        CategoryCardModel(
            title = Sub_Region_Title,
            subtitle = Sub_Region_Subtitle,
            iconRes = R.drawable.ic_subregion,
            iconBackgroundColor = SoftPink,
        ),
        CategoryCardModel(
            title = Currency_Title,
            subtitle = Currency_Subtitle,
            iconRes = R.drawable.ic_currensies,
            iconBackgroundColor = SoftGreen,
        ),
        CategoryCardModel(
            title = Quiz_Title,
            subtitle = Quiz_Subtitle,
            iconRes = R.drawable.ic_question,
            iconBackgroundColor = SoftOrange,
        ),
        CategoryCardModel(
            title = Favorite_Title,
            subtitle = Favorite_Subtitle,
            iconRes = R.drawable.ic_favorite,
            iconBackgroundColor = SoftRed,
        ),
    )

    val rows = categoryList.chunked(2)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp12)
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { category ->
                    CACategoryCard(
                        modifier = Modifier.weight(1f),
                        data = category,
                        onClick = { onSelectedCategory(category.title) }
                    )
                }
            }
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun RecommendedGrid(
    modifier: Modifier = Modifier,
    recommendedList: List<RecommendedCardModel> = emptyList(),
    onSelectedRecommended: (String) -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = (screenWidth * 0.85f) / 2

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp8),
        contentPadding = PaddingValues(bottom = Dimens.dp8)
    ) {
        items(recommendedList) { recommended ->
            CARecommendedCard(
                modifier = Modifier.width(cardWidth),
                data = recommended.copy(onClick = { onSelectedRecommended(recommended.title) })
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    CountriesAppTheme {
        androidx.compose.material3.Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(clickHomeItem = {})
        }
    }
}