package com.sun.structure_android.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sun.structure_android.R
import com.sun.structure_android.data.model.MovieData
import com.sun.structure_android.navigation.BaseDestination
import com.sun.structure_android.presentation.screens.home.components.NowShowingMovieItem
import com.sun.structure_android.presentation.screens.home.components.PopularMovieItem
import com.sun.structure_android.shared.extension.collectAsEffect
import kotlinx.coroutines.flow.collectLatest

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(HomeUiState())
}

@Composable
fun HomeScreen(viewModel: HomeViewModel, navigator: (BaseDestination) -> Unit) {

    viewModel.navigator.collectAsEffect {
        destination -> navigator(destination)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(uiState = uiState, onMovieClick = viewModel::goToMovieDetail)
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    onMovieClick: ((MovieData) -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        TopHeader()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            if (uiState.nowPlayingMovies.isNotEmpty()) {
                SectionTitle(title = "Now Showing", onSeeMoreClick = {
                    Log.d("HomeScreen", "NowShowing onSeeMoreClick")
                })
            }
            Spacer(modifier = Modifier.height(16.dp))
            ListNowShowingMovies(uiState.nowPlayingMovies, onMovieClick = onMovieClick)
            Spacer(modifier = Modifier.height(24.dp))
            if (uiState.popularMovies.isNotEmpty()) {
                SectionTitle(title = "Popular", onSeeMoreClick = {
                    Log.d("HomeScreen", "Popular onSeeMoreClick")
                })
            }
            Spacer(modifier = Modifier.height(16.dp))
            ListPopularMovies(
                movies = uiState.popularMovies,
                onMovieClick = onMovieClick,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TopHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(60.dp)
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
        )
        Text(
            text = "Movie Compose",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SectionTitle(title: String, onSeeMoreClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp, shape = RoundedCornerShape(16.dp), color = Color(0xFFE5E4EA)
                )
                .clip(RoundedCornerShape(16.dp))
                .clickable(
                    onClick = {
                        onSeeMoreClick?.invoke()
                    },
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = rememberRipple(bounded = true),
                )
        ) {
            Text(
                text = "See More",
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                color = Color(0xFFAAA9B1)
            )
        }
    }
}

@Composable
fun ListNowShowingMovies(movies: List<MovieData>, onMovieClick: ((MovieData) -> Unit)? = null) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(movies.size) { index ->
            NowShowingMovieItem(movie = movies[index], onMovieClick = onMovieClick)
        }
    }
}

@Composable
fun ListPopularMovies(
    movies: List<MovieData>,
    onMovieClick: ((MovieData) -> Unit)? = null
) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        repeat(movies.size) {index ->
            PopularMovieItem(movies[index], onMovieClick = onMovieClick)
        }
    }
}
