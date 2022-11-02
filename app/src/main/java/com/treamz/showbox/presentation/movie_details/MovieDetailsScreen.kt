package com.treamz.showbox.presentation.movie_details

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(paramId:String,viewModel: MovieDetailsViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    if(state.isLoading) {
        CircularProgressIndicator()
    }
    if(state.error.isNotBlank()) {
        Text(text = state.error)
    }
    state.movieDetails?.let {
        val localDate = LocalDate.parse(state.movieDetails.release_date)

        Scaffold() {
            LazyColumn() {
                item {
                    Box(modifier = Modifier.background(Color.Green)) {
                        AsyncImage(
                            model = "https://www.themoviedb.org/t/p/w500/${state.movieDetails.poster_path}",
                            contentDescription = "",
                            modifier = androidx.compose.ui.Modifier
                                .width(screenWidth)
                                .height(screenHeight - 100.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Box(
                            modifier = Modifier
                                .size(screenWidth, screenHeight - 100.dp)
                                .background(
                                    Brush.verticalGradient(
                                        0F to Color.Transparent,
                                        .5F to Color.Black.copy(alpha = 0.5F),
                                        1F to MaterialTheme.colors.background
                                    )
                                ),
                        )
                        TopAppBar(backgroundColor = Color.Black.copy(alpha = 0.5f), elevation = 0.dp) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${state.movieDetails?.title}",
                                    style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White),
                                    modifier = Modifier.padding(10.dp)
                                )
                                Box(modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.Blue)) {
                                    Text(text = "${localDate.year}")
                                }
                            }

                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${state.movieDetails.runtime} min.",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f),
                        )

                        Button(
                            onClick = {
//                            navController.navigate(DetailsScreen.Player.route)
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.weight(1f),
                        ) {
                            Text("Watch")
                        }
                        Text(
                            text = "${state.movieDetails.vote_average}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )

                    }
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = "${state.movieDetails?.overview}"
                    )
                }
            }
        }

    }
}