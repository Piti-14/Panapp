package com.example.panapp.ui.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val PASSWORD_LENGTH = 6
class LoginViewModel: ViewModel() {
    private var _email = MutableLiveData<String>()
    var email: LiveData<String> = _email

    private var _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    private var _passwordVisibility = MutableLiveData(false)
    var passwordVisibility: LiveData<Boolean> = _passwordVisibility

    fun validEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validPassword(password: String): Boolean = password.length >= PASSWORD_LENGTH

    fun setEmailValue(newEmail: String){
        _email.value = newEmail
    }

    fun setPasswordValue(newPassword: String){
        _password.value = newPassword
    }

    fun togglePasswordVisibility(): Boolean {
        val active = _passwordVisibility.value
        _passwordVisibility.value = !active!!
        return _passwordVisibility.value!!
    }
}