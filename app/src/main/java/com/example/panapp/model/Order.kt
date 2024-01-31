package com.example.panapp.model

data class Order (
    val list: List<Recipe>,
    val date: String,
    val customer: String,
    val totalCost: Double
)