package com.example.panapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.panapp.model.Ingredient
import com.example.panapp.model.Recipe
import com.example.panapp.ui.components.Measures
import com.example.panapp.ui.components.Quantity

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewRecipe() {
    var context = LocalContext.current

    val listOfIngredients = mutableListOf<Ingredient>()
    var totalRecipeCost by rememberSaveable { mutableStateOf(0) }

    var ingrediente by rememberSaveable { mutableStateOf("") }
    var cantidad by rememberSaveable { mutableDoubleStateOf(0.0) }

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
                modifier = Modifier.width(256.dp)
            )

            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

                Quantity(modifier = Modifier.width(120.dp))

                Measures(modifier = Modifier
                    .width(120.dp)
                    .clip(RectangleShape)
                )

                Button(
                    onClick = {
                        listOfIngredients.add(Ingredient(ingrediente, cantidad, ))
                        ingrediente = ""
                        cantidad = 0.0
                        Toast.makeText(context, "Ingrediente añadido a la receta", Toast.LENGTH_SHORT).show()
                    },
                    enabled = (cantidad != 0.0 && ingrediente != ""),
                ) {
                    Text(text = "Añadir")
                }
            }

        }
    }
}