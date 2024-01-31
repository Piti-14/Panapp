package com.example.panapp.ui.viewmodels

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panapp.model.Order
import java.time.LocalDate

class OrderViewModel: ViewModel() {

    private var _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> = _orders

    fun addOrder(order: Order){
        val orderList = _orders.value.orEmpty().toMutableList()
        orderList.add(order)

        _orders.value = orderList
    }

    fun getOrders(): List<Order>{
        return _orders.value.orEmpty()
    }

    fun getPedidoProximo(today: Int): Order{

        var nextOrder: Order = _orders.value?.first {
            IntOfTheDay(it.date) >= today
        }!!

        return nextOrder
    }

    fun IntOfTheDay(day: String): Int{
        return when(day.lowercase()){
            "lunes" -> 1
            "martes" -> 2
            "miercoles" -> 3
            "miércoles" -> 3
            "jueves" -> 4
            "viernes" -> 5
            "sabado" -> 6
            "sábado" -> 6
            "domingo" -> 7
            else -> -1
        }
    }
}