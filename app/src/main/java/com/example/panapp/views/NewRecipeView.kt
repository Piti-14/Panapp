package com.example.panapp.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NewRecipe() {

    LazyColumn(
        Modifier.fillMaxSize()
    ){
        item { 
            LazyRow(){}
            
            Text(text = "")
        }
    }
}

/*
@Composable
fun
*/
