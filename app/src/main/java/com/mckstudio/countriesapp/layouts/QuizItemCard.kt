package com.mckstudio.countriesapp.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mckstuido.countriesapp.R

@Composable
fun QuizItemCard(
    quizText: String,
    clickItem: (String) -> Unit,
    backgroundColor: Color,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    clickItem.invoke(quizText)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = quizText,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.icons_next),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 10.dp)
            )
        }
    }
}