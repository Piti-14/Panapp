package com.example.panapp.model

data class Ingredient (
    val name: String,
    val qty: Double,
    val measure: String
){
    override fun toString(): String {
        return "$name x $qty $measure"
    }
}