package com.treamz.showbox.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.treamz.showbox.data.remote.dto.movie_disover.ResultDto

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(result: ResultDto?,onClick: (id:Int) -> Unit) {
    var color by remember { mutableStateOf(Color.Black) }
    var scale by remember { mutableStateOf(1f) }
    val focusRequester = FocusRequester()
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = color,
        modifier = Modifier
            .size(120.dp, 200.dp)
            .padding(10.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                color = if (it.isFocused) Color.Green else Color.White
                scale = if (it.isFocused) 1.1f else 1f
            }
//            .onFocusChanged { scale = if (it.isFocused) 1f else 1.5f }
            .scale(scale),
        onClick = {
                onClick(result?.id ?: 0)
//            navController.navigte("details?id=23424")
        }) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${result?.backdrop_path}",
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .size(120.dp, 200.dp)
                .background(
                    Brush.verticalGradient(
                        0F to Color.Transparent,
                        .5F to Color.Black.copy(alpha = 0.5F),
                        1F to Color.Black.copy(alpha = 0.8F)
                    )
                ),
        )
        Box(modifier = Modifier.fillMaxSize().padding(5.dp)) {
            Text(text = "${result?.title}", modifier = Modifier.align(Alignment.BottomStart), color = Color.White)
        }
    }
}



