package com.mckstudio.countriesapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.newmodel.CountryCurrency

data class CountryList(
    val title : String,
    val subtitle : String,
    val imageUrl : String,
)

@Composable
fun CASearchList(
    modifier: Modifier = Modifier,
    countryList : List<CountryList> = emptyList(),
    onSelectedCountry : (String) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.dp12)
    ) {
        countryList.forEach { country ->
            CASearchItem(
                title = country.title,
                subtitle = country.subtitle,
                imageUrl = country.imageUrl,
                isRadius = true,
                isShowArrow = true,
                shape = RoundedCornerShape(Dimens.dp8),
                onClick = {
                    onSelectedCountry(country.title)
                }
            )
        }
    }
}