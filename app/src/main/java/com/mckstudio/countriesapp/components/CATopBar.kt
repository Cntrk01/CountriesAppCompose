package com.mckstudio.countriesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.ui.theme.CABlue
import com.mckstuido.countriesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CATopBar(
    modifier: Modifier = Modifier,
    title: String = "Country App",
    onBackClick: (() -> Unit)? = null,
) {
    Column() {
        Row(
            modifier = modifier
                .height(Dimens.dp60)
                .fillMaxWidth()
                .padding(horizontal = Dimens.dp12, vertical = Dimens.dp12),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (onBackClick != null) {
                    IconButton(
                        modifier = Modifier.size(Dimens.dp30),
                        onClick = onBackClick,
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .fillMaxSize()
                                .rotate(-90f)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(Dimens.dp40)
                            .clip(RoundedCornerShape(Dimens.dp12))
                            .background(CABlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_globe),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(Dimens.dp30)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(Dimens.dp12))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        HorizontalDivider(
            thickness = 0.2.dp,
            color = MaterialTheme.colorScheme.outline
        )
    }
}