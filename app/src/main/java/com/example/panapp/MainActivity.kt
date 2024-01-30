package com.example.panapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.panapp.ui.components.Measures
import com.example.panapp.ui.screens.NewRecipe
import com.example.panapp.ui.screens.Order
import com.example.panapp.ui.screens.WorkFlow
import com.example.panapp.ui.theme.PanappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PanappTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "Login_Page"){
                    //composable("Pruebas"){ Measures()}
                    composable("Login_Page"){}
                    //composable("NewRecipe_Screen"){ NewRecipe(navController) }
                    composable("WorkFlow_Screen"){ WorkFlow(navController) }
                    composable("Orders_Screen"){ Order(navController) }
                }


            }
        }
    }
}