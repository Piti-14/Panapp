package com.example.panapp.model

data class Recipe (
    val ingredients: List<Ingredient> = emptyList(),
    val cost: Double = 0.0,
    val name: String = ""
)