package com.example.panapp.model

import androidx.compose.ui.Modifier

sealed class RecipeItem{
    abstract fun paint()
}

data class RecipeTextItem (
    val modifier: Modifier,
) : RecipeItem() {
    override fun paint() {
        TODO("Not yet implemented")
    }
}
