/*
package com.example.panapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardType
import androidx.compose.foundation.text.KeyboardType.Companion.Text
import androidx.compose.foundation.text.KeyboardType.Companion.Date
import androidx.compose.foundation.text.KeyboardType.Companion.DateTime
import androidx.compose.foundation.text.KeyboardType.Companion.Time
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.ArrowDropDown
import androidx.compose.material3.icons.filled.DateRange
import androidx.compose.material3.icons.filled.Done
import androidx.compose.material3.icons.filled.Person
import androidx.compose.material3.icons.filled.ShoppingCart
import androidx.compose.material3.icons.outlined.Cake
import androidx.compose.material3.icons.outlined.RestaurantMenu
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalDensityOwner
import androidx.compose.ui.platform.LocalDensityOwnerAmbient
import androidx.compose.ui.platform.LocalDensityOwnerAmbient.currentDensity
import androidx.compose.ui.platform.LocalDensityOwnerAmbient.currentDensity
import androidx.compose.ui.platform.LocalDensityOwnerAmbient.currentDensity
import androidx.compose.ui.platform.LocalDensityOwnerAmbient.currentDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.LocalViewTreeObserver
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.LocalWindowManager
import androidx.compose.ui.platform.LocalZOrder
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType.Companion.Date
import androidx.compose.ui.text.input.KeyboardType.Companion.Number
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.keyboardOptions
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PedidoScreen()
        }
    }
}

@Composable
fun PedidoScreen() {
    var nombreCliente by remember { mutableStateOf("") }
    var fechaEntrega by remember { mutableStateOf<Date?>(null) }
    var productosSeleccionados by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nombre del cliente
        OutlinedTextField(
            value = nombreCliente,
            onValueChange = { nombreCliente = it },
            label = { Text("Nombre del Cliente") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Fecha de entrega
        DatePicker(
            selectedDate = fechaEntrega,
            onDateSelected = { fechaEntrega = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Productos
        ProductosSelector(
            productosSeleccionados = productosSeleccionados,
            onProductosSelected = { productosSeleccionados = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Botón de registro
        Button(
            onClick = {
                // Aquí puedes hacer lo que necesites con los datos del pedido
                // Por ejemplo, imprimirlos en la consola
                println("Nombre del Cliente: $nombreCliente")
                println("Fecha de Entrega: $fechaEntrega")
                println("Productos Seleccionados: $productosSeleccionados")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = null)
            Text("Registrar Pedido")
        }
    }
}

@Composable
fun DatePicker(
    selectedDate: Date?,
    onDateSelected: (Date?) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedDate?.toString() ?: "",
            onValueChange = {},
            label = { Text("Fecha de Entrega") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        if (expanded) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                CalendarView(
                    onDateSelected = { date ->
                        onDateSelected(date)
                        expanded = false
                    },
                    selectedDate = selectedDate,
                    context = context
                )
            }
        }
    }
}

@Composable
fun ProductosSelector(
    productosSeleccionados: List<String>,
    onProductosSelected: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val productos = listOf(
        "Dos barras de pan",
        "Tres croissants",
        "Una magdalena",
        "Una barra de pan",
        "Una ensaimada"
    )

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = productosSeleccionados.joinToString(", "),
            onValueChange = {},
            label = { Text("Productos Seleccionados") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        if (expanded) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                productos.forEach { producto ->
                    DropdownMenuItem(
                        onClick = {
                            onProductosSelected(
                                if (productosSeleccionados.contains(producto)) {
                                    productosSeleccionados - producto
                                } else {
                                    productosSeleccionados + producto
                                }
                            )
                            expanded = false
                        }
                    ) {
                        Text(producto)
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarView(
    onDateSelected: (Date) -> Unit,
    selectedDate: Date?,
    context: android.content.Context
) {
    var selectedDate by remember { mutableStateOf(Calendar.getInstance().time) }

    Calendar(
        selectedDate = selectedDate,
        onDateSelected = {
            selectedDate = it
            onDateSelected(it)
        },
        context = context
    )
}

@Composable
fun Calendar(
    selectedDate: Date,
    onDateSelected: (Date) -> Unit,
    context: android.content.Context
) {
    val calendarView = remember { android.widget.CalendarView(context) }

    DisposableEffect(context) {
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            onDateSelected(calendar.time)
        }

        onDispose {
            // Dispose resources here if needed
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        AndroidView({ calendarView }) { view ->
            // Do nothing here, as the view is created and controlled by Android
        }
    }
}
*/
