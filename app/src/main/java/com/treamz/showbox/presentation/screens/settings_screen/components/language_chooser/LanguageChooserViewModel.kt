package com.treamz.showbox.presentation.screens.settings_screen.components.language_chooser

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.treamz.showbox.data.preferences.PREF_CHOOSED_LANGUAGE
import com.treamz.showbox.data.preferences.PrefRepository
import com.treamz.showbox.domain.models.language.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LanguageChooserViewModel @Inject constructor(
    val prefRepository: PrefRepository

) : ViewModel() {
    private val _state = mutableStateOf(LanguageChooserState())
    val state: State<LanguageChooserState> = _state
    init {
        _state.value = LanguageChooserState(pickedLanguage = prefRepository.getLanguage())
    }

    fun newValue(language: Language) {
        prefRepository.setLanguage(language)
        _state.value = LanguageChooserState(pickedLanguage = language)
    }
}