package com.example.panapp.ui.components

import android.view.RoundedCorner
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true)
@Composable
fun Quantity(modifier: Modifier){

    var cantidad by remember { mutableDoubleStateOf(0.0) }

    Row {
        TextField(
            value = "$cantidad",
            onValueChange = {cantidad = it.toDouble()},
            label = { Text("Cantidad") },
            modifier = modifier
        )
    }
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
//@Preview(showSystemUi = true)
@Composable
fun Measures(modifier: Modifier){
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
                    onClick = { unidad = measure }
                )
            }
        }
    }
}
