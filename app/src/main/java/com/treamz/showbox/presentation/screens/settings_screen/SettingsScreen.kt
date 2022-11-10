package com.treamz.showbox.presentation.screens.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.treamz.showbox.presentation.screens.settings_screen.components.language_chooser.LanguageChooser


@Composable
fun SettingsScreen() {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            LanguageChooser()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Sources", modifier = Modifier.padding(20.dp))
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Author Ivan Chernoknizhikov (Treamz)", modifier = Modifier.padding(20.dp))
            }
        }
    }
}