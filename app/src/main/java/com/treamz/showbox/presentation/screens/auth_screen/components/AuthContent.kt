package com.treamz.showbox.presentation.screens.auth_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.treamz.showbox.presentation.screens.auth_screen.AuthViewModel

@Composable
fun AuthContent(
    isAuthrized: AuthViewModel,
    padding: PaddingValues,
    oneTapSignIn: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            SignInWithEmailAndPassword()
        }
        Box {
            Divider()
        }
//        Text(text =isAuthrized.firebaseUser.value?.uid.toString() )
        Box(
            modifier = Modifier
                .padding(padding),
            contentAlignment = Alignment.BottomCenter
        ) {
            SignInButton(
                onClick = oneTapSignIn
            )
        }
    }
}