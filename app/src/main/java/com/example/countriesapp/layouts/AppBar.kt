package com.example.countriesapp.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.countriesapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppBar(
    backClick: (() -> Unit)? = null,
    backButtonCheck: Boolean = false,
    endButtonCheck: Boolean = false,
    imageId: Int,
) {
    val coroutineScope = rememberCoroutineScope()
    val checkBackButton = remember { mutableStateOf(backButtonCheck) }
    val checkEndButton = remember { mutableStateOf(endButtonCheck) }

    Row(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(0.3f)
                .height(50.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            if (checkBackButton.value) {
                Image(
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .clickable {
                            if (checkBackButton.value){
                                backClick?.invoke()
                            }
                        },
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "",
                    alignment = Center
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(0.3f)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (checkEndButton.value) {
                Image(
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .clickable {
                            //backClick?.invoke()
                        },
                    painter = painterResource(id = R.drawable.icons_jordan),
                    contentDescription = "",
                    alignment = Center
                )
            }
        }
    }


}
