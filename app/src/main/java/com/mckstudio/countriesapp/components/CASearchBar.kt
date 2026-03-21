package com.mckstudio.countriesapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.ui.theme.CountriesAppTheme
import com.mckstuido.countriesapp.R

@Composable
fun CASearchBar(
    modifier: Modifier = Modifier,
    onSearchTextChange: (String) -> Unit,
    searchText: String = "",
    placeholder: String = "Search countries, capitals...",
) {
    BasicTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = Dimens.dp1,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(Dimens.dp12)
            )
            // Arka plan dolgusu temadaki 'surfaceVariant' slotundan geliyor
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(Dimens.dp12)
            )
            .padding(horizontal = Dimens.dp16, vertical = Dimens.dp12),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    modifier = Modifier.size(Dimens.dp24),
                    painter = painterResource(R.drawable.icon_search),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.width(Dimens.dp8))

                Box(modifier = Modifier.weight(1f)) {
                    if (searchText.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CASearchBarPreview() {
    CountriesAppTheme {
        CASearchBar(
            onSearchTextChange = {

            }
        )
    }
}
