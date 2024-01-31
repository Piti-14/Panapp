package com.example.panapp.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.panapp.model.Ingredient
import com.example.panapp.model.Recipe


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Quantity(modifier: Modifier): Double{

    var cantidad by remember { mutableDoubleStateOf(0.0) }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = cantidad.toString(),
        onValueChange = {
            isError = try {
                cantidad = it.toDouble()
                false
            } catch (e: NumberFormatException) {
                true
            }
        },
        modifier = modifier,
        singleLine = true,
        isError = isError,
        label = { Text("Cantidad") },
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number ),
        visualTransformation = if (isError) VisualTransformation.None else VisualTransformation.None
    )

    return cantidad
}

fun getMeasures(): List<String> {
    return listOf(
        "Kg",
        "g",
        "L",
        "ml",
        "oz",
        "lb",
    )
}

@Composable
fun Measures(modifier: Modifier): String{
    val measures = getMeasures()
    var expanded by rememberSaveable { mutableStateOf(false) }
    var unidad by rememberSaveable { mutableStateOf("Medida") }

    Row {
        Button(
            onClick = { expanded = !expanded },
            modifier = modifier
        ) {
            Text(unidad)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ){
            measures.forEach { measure ->
                DropdownMenuItem(
                    text = { Text(measure) },
                    onClick = {
                        unidad = measure
                        expanded = false
                    }
                )
            }
        }
    }

    return unidad
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceTagAndSaveButton(ingredients: List<Ingredient>, context: Context): Recipe {
    
    var precio by rememberSaveable { mutableStateOf("") }
    var nuevaReceta = remember<Recipe> { mutableStateOf(null) }



    return nuevaReceta
}*/

@Composable
fun RecipePreview(ingredients: List<Ingredient>){

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true,
        modifier = Modifier
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            .height(if (ingredients.isEmpty()) 0.dp else 300.dp)
    ){
        items(ingredients.size){
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
                        text = "Ingrediente nº ${it + 1}: ${ingredients[it].name}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = ingredients[it].qty.toString() + " " + ingredients[it].measure,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
