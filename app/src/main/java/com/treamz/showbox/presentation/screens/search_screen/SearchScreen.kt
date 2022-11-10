package com.treamz.showbox.presentation.screens.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.treamz.showbox.data.remote.dto.multi_search.SearchResult
import com.treamz.showbox.domain.models.multi_search.MultiSearch


@Composable
fun SearchScreen(
    navController: NavController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {
    val state = searchScreenViewModel.state
    val stateSearchQuery = searchScreenViewModel.state.value.searchQuery;
    val textState = remember { mutableStateOf(TextFieldValue(stateSearchQuery)) }
    Column {
        SearchView(textState, searchScreenViewModel)
        SearchListResults(
            navController = navController,
            state = searchScreenViewModel.state.value.multiSearchdata
        )
    }
}


@Composable
fun SearchView(state: MutableState<TextFieldValue>, searchScreenViewModel: SearchScreenViewModel) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            searchScreenViewModel.sendQuery(value.text)

            state.value = value

        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun CountryListItem(countryText: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(countryText) })
            .background(MaterialTheme.colors.primary)
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = countryText, fontSize = 18.sp, color = Color.White)
    }
}

@Composable
fun SearchListResults(
    navController: NavController, state:
    MultiSearch?
) {
    state?.results.let {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state?.results?.size ?: 0) { id ->
                buildItem(item = state?.results?.get(id), navController)
            }
        }
    }

}

@Composable
fun buildItem(item: SearchResult?, navController: NavController) {
    when (item?.media_type) {
        "tv" -> {
            Text("TV")
        }
        "person" -> {
            Text("TV")
        }
        "movie" -> {
            MovieItemSearch(item,navController)
        }
    }

}

@Composable
fun MovieItemSearch(item: SearchResult?,navController: NavController) {

    val overview : String =  item?.overview?.let {
        if(it.length < 180) it else it.substring(0,180) + ".."
    } ?: ""
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clickable {
                navController.navigate("movieDetails/${item?.id}")
            }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${item?.poster_path}",
                contentDescription = "",
                modifier = Modifier.size(width = 100.dp, height = 150.dp),
                contentScale = ContentScale.Crop,

                )
            Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
                Text(text = item?.title ?: "")
                Text(text = overview)
            }
        }
    }
}