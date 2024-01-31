package com.example.panapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panapp.model.Recipe

class RecipeViewModel: ViewModel() {

    private var _products = MutableLiveData<List<Recipe>>()
    val products: LiveData<List<Recipe>> = _products

    fun addRecipe(recipe: Recipe) {
        val currentList = _products.value.orEmpty().toMutableList()
        currentList.add(recipe)
        _products.value = currentList
    }

    fun getRecipes(): List<Recipe> {
        return _products.value.orEmpty()
    }

    fun findRecipeByName(name: String): Recipe? {
        val selected = _products.value.orEmpty().firstOrNull() { recipe ->
            recipe.name == name
        }

        return selected
    }
}