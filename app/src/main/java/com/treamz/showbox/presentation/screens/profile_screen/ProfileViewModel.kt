package com.treamz.showbox.presentation.screens.profile_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treamz.showbox.domain.models.Response
import com.treamz.showbox.domain.repository.AuthRepository
import com.treamz.showbox.domain.repository.ProfileRepository
import com.treamz.showbox.domain.repository.RevokeAccessResponse
import com.treamz.showbox.domain.repository.SignOutResponse
import com.treamz.showbox.presentation.screens.show_details.ShowDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ProfileRepository,
    private val repo2: AuthRepository
) : ViewModel() {
    val displayName get() = repo.displayName
    val photoUrl get() = repo.photoUrl

    private val _state = mutableStateOf(TmdbSessionState())
    val state: State<TmdbSessionState> = _state

    init {
        getSession()
    }

    fun getSession() {
        viewModelScope.launch {
            val sessionDto = repo.getGuestSessionTMDB()
            sessionDto.let {
                if (it != null) _state.value = TmdbSessionState(sessionDto)
            }

        }
    }

    var signOutResponse by mutableStateOf<SignOutResponse>(Response.Success(false))
        private set
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Response.Success(false))
        private set

    fun signOut() = viewModelScope.launch {
        signOutResponse = Response.Loading
        signOutResponse = repo.signOut()
       repo2.logout()
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Response.Loading
        revokeAccessResponse = repo.revokeAccess()
    }
}