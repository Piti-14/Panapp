package com.example.panapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panapp.model.Order

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
}