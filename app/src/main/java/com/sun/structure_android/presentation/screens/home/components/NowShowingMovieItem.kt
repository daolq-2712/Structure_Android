package com.sun.structure_android.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sun.structure_android.R
import com.sun.structure_android.ui.theme.AppColors

@Preview(showBackground = true)
@Composable
fun NowShowingMovieItemPreview() {
    NowShowingMovieItem()
}

@Composable
fun NowShowingMovieItem() {
    Column(
        modifier = Modifier
            .width(143.dp)
            .wrapContentHeight()
    ) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = AppColors.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(143.dp)
                    .height(212.dp),
                model = "https://image.tmdb.org/t/p/original/reEMJA1uzscCbkpeRJeTT2bjqUp.jpg",
                contentDescription = "Translated description of what the image contains"
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Spiderman: No way home",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star), contentDescription = null,
                modifier = Modifier.size(12.dp), tint = AppColors.LightningYellow
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "8.5/10 IMDB", fontSize = 12.sp, color = AppColors.DustyGray)
        }
    }
}
