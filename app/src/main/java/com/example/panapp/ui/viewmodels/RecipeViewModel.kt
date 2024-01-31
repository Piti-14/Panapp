package com.example.panapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panapp.model.Recipe

class RecipeViewModel: ViewModel() {

    private var _products = MutableLiveData<MutableList<Recipe>>()
    val products: LiveData<MutableList<Recipe>> = _products

    fun addRecipe(recipe: Recipe) {
        _products!!.value?.add(recipe)
    }

    fun getList(): List<Recipe> {
        return _products.value as List<Recipe>
    }
}