package com.example.panapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.panapp.model.Order

class OrderViewModel: ViewModel() {

    private var _orders = MutableLiveData<MutableList<Order>>()
    val oders: LiveData<MutableList<Order>> = _orders

    fun addOrder(order: Order){
        _orders.value?.add(order)
    }
}