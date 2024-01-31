package com.example.panapp.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.panapp.R
import com.example.panapp.ui.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginData: LoginViewModel, context: Context, navController: NavHostController) {

    val email by loginData.email.observeAsState("")
    val password by loginData.password.observeAsState("")
    val passwordVisibility by loginData.passwordVisibility.observeAsState(initial = false)

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Logo_Panapp",
                Modifier
                    .clip(CircleShape)
                    .size(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        //EMAIL
        item {
            TextField(
                value = email,
                onValueChange = { loginData.setEmailValue(it) },
                label = { Text(text = "Email") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        //PASSWORD
        item {
            TextField(
                value = password,
                onValueChange = { loginData.setPasswordValue(it) },
                label = { Text(text = "Password") },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { loginData.togglePasswordVisibility() }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Visibility"
                        )
                    }
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(
                onClick = {
                    Toast.makeText(context, "Login in...", Toast.LENGTH_SHORT).show()
                    loginData.setEmailValue("")
                    loginData.setPasswordValue("")
                    navController.navigate("NewRecipe_Screen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp),
                enabled = ( loginData.validEmail(email) && loginData.validPassword(password) )
            ) {
                Text(text = "Log in", color = Color.White)
            }
        }
    }
}