package com.example.panapp.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.panapp.ui.viewmodels.OrderViewModel

@Composable
fun WorkFlow(context: Context, orderViewModel: OrderViewModel){

    val orders by orderViewModel.orders.observeAsState()
    var clientName by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Column (
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            Alignment.CenterHorizontally
        ){
            Text(
                text = "FLUJO DE TRABAJO",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = true,
                modifier = Modifier
                    .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
                    .height(if (orders?.isEmpty() == true) 0.dp else 300.dp)
            ){
                orders?.let { items(it.size){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .background(Color.LightGray),
                            //.padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        clientName = orders!![it].customer
                        Text(
                            text = "Pedido actual: $clientName",
                            fontWeight = FontWeight.Bold
                        )

                        orders!![it].list.forEach { recipe -> 
                            Text(
                                text = "${recipe.name}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } }
            }

            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                //orders.f  SACAR FECHA
                Text(
                    text = "Fecha: ${orders?.get(0)?.date} - Tiempo Estimado: ${(30..180).random()} min"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row (

            ){
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Historial Pedidos")
                }

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Siguiente Pedido")
                }
            }
        }
    }
}