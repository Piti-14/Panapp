package com.example.panapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineStops
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.panapp.ui.screens.LoginScreen
import com.example.panapp.ui.screens.NewOrder
import com.example.panapp.ui.screens.NewRecipe
import com.example.panapp.ui.screens.WorkFlow
import com.example.panapp.ui.theme.PanappTheme
import com.example.panapp.ui.viewmodels.LoginViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PanappTheme {

                val context = LocalContext.current
                val navController = rememberNavController()
                var snackBarState = remember { SnackbarHostState() }

                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackBarState) },
                    bottomBar = { ToolBar(navController) },
                ){
                    Column (
                        Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = it.calculateBottomPadding()
                            )){
                        NavHost(navController = navController, startDestination = "Login_Screen"){
                            composable("Login_Screen"){ LoginScreen(loginData = LoginViewModel(), context, navController) }
                            composable("NewRecipe_Screen"){ NewRecipe(context) }
                            composable("WorkFlow_Screen"){ WorkFlow(navController) }
                            composable("Orders_Screen"){ NewOrder(context) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToolBar(navController: NavController){
    BottomAppBar {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ){

            IconButton(
                onClick = {
                    navController.navigate("Orders_Screen")
                },
                modifier = Modifier.width(90.dp)
            ){
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(imageVector = Icons.Default.Task, contentDescription = "Orders")
                    Text(
                        text = "PEDIDOS",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            IconButton(
                onClick = {
                    navController.navigate("NewRecipe_Screen")
                },
                modifier = Modifier.width(90.dp)
            ){
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Icon(imageVector = Icons.Default.Fastfood, contentDescription = "Recipes")
                    Text(
                        text = "RECETAS",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            IconButton(
                onClick = {
                    navController.navigate("WorkFlow_Screen")
                },
                modifier = Modifier.width(90.dp)
            ){
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.AirlineStops, contentDescription = "Tasks")
                    Text(
                        text = "TAREAS",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}