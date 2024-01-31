package com.example.panapp.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.panapp.model.Order
import com.example.panapp.model.Recipe
import com.example.panapp.ui.components.OrderPreview
import com.example.panapp.ui.components.Products
import com.example.panapp.ui.components.Unidades
import com.example.panapp.ui.viewmodels.OrderViewModel
import com.example.panapp.ui.viewmodels.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewOrder(context: Context, recipeViewModel: RecipeViewModel, orderViewModel: OrderViewModel) {

    var listOfProducts = rememberSaveable { mutableListOf<Recipe>() } //Lista de productos de un pedido de un cliente
    var totalOrderCost by rememberSaveable { mutableStateOf(0.0) }
    var precio by rememberSaveable { mutableStateOf("") }
    var cliente by rememberSaveable { mutableStateOf("") }
    var cantidad by rememberSaveable { mutableStateOf(0) }
    var fecha by rememberSaveable { mutableStateOf("") }
    var recetas = rememberSaveable { mutableListOf<Recipe>() }
    //var productoActual by rememberSaveable { mutableStateOf<Recipe>() }

    var products = recipeViewModel.products.observeAsState() //Productos que vendo
    var orders = orderViewModel.orders.observeAsState() //Pedidos pendientes

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            //horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text(
                text = "NUEVO PEDIDO",
                Modifier
                    .fillMaxWidth()
                    .padding(start = 80.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            TextField(
                value = cliente,
                onValueChange = {cliente = it},
                label = { Text("Nombre cliente")},
                modifier = Modifier.width(256.dp),
                singleLine = true
            )

            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

               val productoActual = Products(
                                     modifier = Modifier
                                         .width(120.dp)
                                         .clip(RectangleShape),
                                     recipeViewModel
                                 )

                cantidad = Unidades(modifier = Modifier.width(120.dp))

                Button(
                    onClick = {
                        if (productoActual != null){
                            listOfProducts.add(productoActual)
                            cantidad = 0
                            Toast.makeText(context, "Ingrediente añadido a la receta", Toast.LENGTH_SHORT).show()
                        }
                    },
                    enabled = (((cantidad != 0) && ( productoActual?.name != "Producto") && cliente != "")),
                ) {
                    Text(text = "Añadir")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            totalOrderCost = OrderPreview(listOfProducts, cantidad)

            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ){

                TextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text(text = "Fecha")},
                    singleLine = true,
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                )

                Button(
                    modifier = Modifier.clip(RectangleShape),
                    onClick = {
                        if (fecha != "" && cliente != "") {
                            val nuevoPedido = Order(listOfProducts, fecha, cliente, totalOrderCost)
                            orderViewModel.addOrder(nuevoPedido)
                            precio = ""
                            fecha = ""
                            cliente = ""
                            listOfProducts.clear()
                            Toast.makeText(context, "Nuevo pedido generado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Ingrese precio y/o fecha válidos", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(text = "CREAR PEDIDO")
                }
            }
        }
    }
}