package com.example.soundinch9.ui.viewmodel

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

    fun login(name: String, email: String) {
        _userName.value = name
        _userEmail.value = email
        _isLoggedIn.value = true
    }

    fun logout() {
        _userName.value = ""
        _userEmail.value = ""
        _isLoggedIn.value = false
    }
}