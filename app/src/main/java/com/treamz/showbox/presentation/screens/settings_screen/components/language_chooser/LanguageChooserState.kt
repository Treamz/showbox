package com.treamz.showbox.presentation.screens.settings_screen.components.language_chooser

import com.treamz.showbox.domain.models.language.Language

data class LanguageChooserState(
    val pickedLanguage: Language = Language(name = "English", "EN_us")
)