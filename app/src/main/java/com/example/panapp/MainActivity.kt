package com.example.panapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.panapp.ui.screens.LoginScreen
import com.example.panapp.ui.screens.NewRecipe
import com.example.panapp.ui.screens.Order
import com.example.panapp.ui.screens.WorkFlow
import com.example.panapp.ui.theme.PanappTheme
import com.example.panapp.ui.viewmodels.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PanappTheme {
                val context = LocalContext.current
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "NewRecipe_Screen"){
                    composable("Login_Screen"){ LoginScreen(loginData = LoginViewModel(), context, navController) }
                    composable("NewRecipe_Screen"){ NewRecipe(navController, context) }
                    composable("WorkFlow_Screen"){ WorkFlow(navController) }
                    composable("Orders_Screen"){ Order(navController) }
                }


            }
        }
    }
}