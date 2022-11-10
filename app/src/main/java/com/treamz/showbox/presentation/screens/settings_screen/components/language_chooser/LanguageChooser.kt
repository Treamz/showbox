package com.treamz.showbox.presentation.screens.settings_screen.components.language_chooser

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.treamz.showbox.domain.models.language.Language


@Composable
fun LanguageChooser(languageChooserViewModel: LanguageChooserViewModel = hiltViewModel()) {
    val state = languageChooserViewModel.state.value
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(Language(name = "Ukraine", code = "uk-UA"),Language(name = "English", code = "en-US"))
    var selectedIndex by remember { mutableStateOf(items.indexOf(state.pickedLanguage)) }
    val gson = Gson()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    state.let {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
                .padding(10.dp)
                .clickable(onClick = { expanded = true })
        ) {
            Text(text = "Language: ${items[selectedIndex].name}", modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth())
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(screenWidth-10.dp)
                    .background(
                        MaterialTheme.colors.primary
                    ).padding(10.dp)
            ) {
                items.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        expanded = false
                        languageChooserViewModel.newValue(items[selectedIndex])
                    }) {

                        Text(text = s.name)
                    }
                }
            }
        }
    }


}