package com.treamz.showbox.presentation.screens.auth_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.treamz.showbox.R


@Composable
fun SignInWithEmailAndPassword() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TextField(
            value = TextFieldValue("Login"), onValueChange = {

            }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        TextField(
            value = TextFieldValue("Password"), onValueChange = {

            }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Button(modifier = Modifier.padding(bottom = 48.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.purple_700)
            ), onClick = { /*TODO*/ }) {
            Text(text = "LogIn")
        }
    }
}