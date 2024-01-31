package com.example.panapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.panapp.model.Recipe
import com.example.panapp.ui.viewmodels.RecipeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Unidades(modifier: Modifier): Int{

    var cantidad by remember { mutableStateOf(0) }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = cantidad.toString(),
        onValueChange = {
            isError = try {
                cantidad = it.toInt()
                false
            } catch (e: NumberFormatException) {
                true
            }
        },
        modifier = modifier,
        singleLine = true,
        isError = isError,
        label = { Text("Unidades") },
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number ),
        visualTransformation = if (isError) VisualTransformation.None else VisualTransformation.None
    )

    return cantidad
}

@Composable
fun Products(modifier: Modifier, recipeViewModel: RecipeViewModel): Recipe? {

    var expanded by rememberSaveable { mutableStateOf(false) }
    var productos by rememberSaveable { mutableStateOf("Productos") }

    Row {
        Button(
            onClick = { expanded = !expanded },
            modifier = modifier
        ) {
            Text(productos)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ){
            recipeViewModel.products.value?.forEach { product ->
                DropdownMenuItem(
                    text = { Text(product.name) },
                    onClick = {
                        productos = product.name
                        expanded = false
                    }
                )
            }
        }
    }

    return recipeViewModel.findRecipeByName(productos)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderPreview(products: List<Recipe>, qty: Int): Double{
    var totalLineaPedido = 0.0
    var totalPedido by rememberSaveable { mutableStateOf(0.0) }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true,
        modifier = Modifier
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            .height(if (products.isEmpty()) 0.dp else 300.dp)
    ){
        items(products.size){
            totalLineaPedido += (products[it].cost * qty)
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
            ){
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Producto nº${it + 1}: ${products[it].name}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$qty x ${products[it].cost}",
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }

        item {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    horizontalArrangement = Arrangement.End
                ){
                    TextField(
                        value = totalLineaPedido.toString(),
                        onValueChange = { },
                        label = {
                            Text(
                                text = "Precio Pedido",
                                color = Color.Black
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .width(150.dp)
                            .height(50.dp),
                        readOnly = true,
                    )
                }
            }
        }
    }

    DisposableEffect(Unit) {
        totalPedido = totalLineaPedido
        onDispose { }
    }
    return totalPedido
}
