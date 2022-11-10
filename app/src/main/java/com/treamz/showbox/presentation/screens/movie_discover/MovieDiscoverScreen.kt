package com.treamz.showbox.presentation.screens.movie_discover

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.treamz.showbox.presentation.components.MovieCard


@Composable
fun MovieDiscoverScreen(movieDiscoverViewModel: MovieDiscoverViewModel = hiltViewModel()) {
    val movieDiscover = movieDiscoverViewModel.state.value.movieDiscover.collectAsLazyPagingItems()
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(movieDiscover.itemCount) { movieId ->
            MovieCard(result = movieDiscover.get(movieId), onClick = {})
        }
    }
}