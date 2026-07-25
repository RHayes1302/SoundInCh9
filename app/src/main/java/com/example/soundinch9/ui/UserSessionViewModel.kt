package com.example.soundinch9.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserSessionViewModel : ViewModel() {

    private val _userName = MutableStateFlow("")
    private val _userEmail = MutableStateFlow("")
    private val _isLoggedIn = MutableStateFlow(false)

    val userName: StateFlow<String> = _userName.asStateFlow()
    val userEmail: StateFlow<String> = _userEmail.asStateFlow()
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun onLoginSuccess(name: String, email: String) {
        _userName.value = name
        _userEmail.value = email
        _isLoggedIn.value = true
    }

    fun onRegisterSuccess(name: String, email: String) {
        _userName.value = name
        _userEmail.value = email
        _isLoggedIn.value = true
    }

    fun onLogout() {
        _userName.value = ""
        _userEmail.value = ""
        _isLoggedIn.value = false
    }
}