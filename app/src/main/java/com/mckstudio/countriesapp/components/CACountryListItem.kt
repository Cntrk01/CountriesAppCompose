package com.mckstudio.countriesapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.ui.theme.CountriesAppTheme
import com.mckstuido.countriesapp.R

@Composable
fun CACountryListItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    imageUrl: String?,
    isRadius: Boolean = false,
    isShowArrow: Boolean = false,
    shape : RoundedCornerShape = RoundedCornerShape(Dimens.dp0),
    onClick: (() -> Unit) ?= null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(MaterialTheme.colorScheme.surface)
            .then(
                other =
                    if (isRadius) Modifier
                        .border(
                            width = Dimens.dp1,
                            color = MaterialTheme.colorScheme.secondary,
                            shape = shape,
                        )
                    else Modifier
            )
            .clickable {
                onClick?.invoke()
            }
            .padding(vertical = Dimens.dp12, horizontal = Dimens.dp16),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier = Modifier
                .size(50.dp),
            shape = if (isRadius)
                RoundedCornerShape(Dimens.dp16)
            else
                CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant,
            shadowElevation = 2.dp,
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(Dimens.dp16))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        if (isShowArrow){
            Icon(
                modifier = Modifier
                    .size(Dimens.dp24),
                painter = painterResource(R.drawable.ic_arrow_right),
                tint = MaterialTheme.colorScheme.surfaceVariant,
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CACountryListItemPreview() {
    CountriesAppTheme(dynamicColor = false) {
        Column(
            modifier = Modifier.padding(Dimens.dp12)
                .background(MaterialTheme.colorScheme.background)
        ) {
            CACountryListItem(
                title = "Türkiye",
                subtitle = "Türk Lirası ₺",
                imageUrl = "https://flagcdn.com/w320/tr.png"
            )
        }
    }
}