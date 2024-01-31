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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.panapp.model.Ingredient
import com.example.panapp.model.Recipe
import com.example.panapp.ui.components.Measures
import com.example.panapp.ui.components.Quantity
import com.example.panapp.ui.components.RecipePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRecipe(navController: NavHostController, context: Context) {

    val listOfIngredients = rememberSaveable { mutableListOf<Ingredient>() }
    var totalRecipeCost by rememberSaveable { mutableStateOf(0) }
    var precio by rememberSaveable { mutableStateOf("") }
    var ingrediente by rememberSaveable { mutableStateOf("") }
    var cantidad by rememberSaveable { mutableDoubleStateOf(0.0) }
    var medida by rememberSaveable { mutableStateOf("") }
    var recetas = rememberSaveable { mutableListOf<Recipe>() }

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
                text = "NUEVA RECETA",
                Modifier
                    .fillMaxWidth()
                    .padding(start = 80.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            TextField(
                value = ingrediente,
                onValueChange = {ingrediente = it},
                label = { Text("Ingrediente")},
                modifier = Modifier.width(256.dp),
                singleLine = true
            )

            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

                cantidad = Quantity(modifier = Modifier.width(120.dp))

                medida = Measures(modifier = Modifier
                    .width(120.dp)
                    .clip(RectangleShape)
                )

                Button(
                    onClick = {
                        listOfIngredients.add(Ingredient(ingrediente, cantidad, medida))
                        ingrediente = ""
                        cantidad = 0.0
                        Toast.makeText(context, "Ingrediente añadido a la receta", Toast.LENGTH_SHORT).show()
                    },
                    enabled = (((cantidad != 0.0) && (ingrediente != "") && medida != "Medida")),
                ) {
                    Text(text = "Añadir")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            RecipePreview(listOfIngredients)

            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ){
                TextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text(text = "Precio")},
                    singleLine = true,
                    modifier = Modifier.width(100.dp).height(50.dp)
                )

                Button(
                    modifier = Modifier.clip(RectangleShape),
                    onClick = {
                        if (precio.isNotBlank()) {
                            val nuevaReceta = Recipe(listOfIngredients, precio.toDouble())
                            recetas.add(nuevaReceta)
                            precio = ""
                            Toast.makeText(context, "Receta de producto creada", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Ingrese un precio válido", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(text = "GUARDAR")
                }
            }

        }
    }
}