package com.mckstudio.countriesapp.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mckstuido.countriesapp.R

@Composable
fun AppBar(
    backClick: (() -> Unit)? = null,
    imageId: Int = R.drawable.icon_app_bar,
) {
    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (backClick != null) {
            Image(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = LocalIndication.current,
                        onClick = backClick
                    )
                    .size(24.dp),
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "",
                alignment = Center
            )
        } else {
            Spacer(modifier = Modifier.size(24.dp))
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Center
        ) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = imageId),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
    }
}