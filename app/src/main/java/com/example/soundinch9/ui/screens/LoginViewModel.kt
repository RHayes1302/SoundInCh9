package com.example.soundinch9.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _rememberSession = MutableStateFlow(false)
    val rememberSession: StateFlow<Boolean> = _rememberSession.asStateFlow()

    private val _emailError = MutableStateFlow(false)
    val emailError: StateFlow<Boolean> = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow(false)
    val passwordError: StateFlow<Boolean> = _passwordError.asStateFlow()

    // One-off events (snackbar messages) shouldn't be regular state, since state
    // replays its last value on every new collector (e.g. after rotation), which
    // would re-show a stale snackbar. A SharedFlow with no replay avoids that.
    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage: SharedFlow<String> = _snackbarMessage

    fun onEmailChange(value: String) {
        _email.value = value
        _emailError.value = false
    }

    fun onPasswordChange(value: String) {
        _password.value = value
        _passwordError.value = false
    }

    fun onRememberSessionChange(value: Boolean) {
        _rememberSession.value = value
    }

    fun validateAndLogin() {
        val isEmailValid = _email.value.contains('@') && _email.value.contains('.')
        val isPasswordValid = _password.value.length >= 6

        _emailError.value = !isEmailValid
        _passwordError.value = !isPasswordValid

        viewModelScope.launch {
            if (!isEmailValid || !isPasswordValid) {
                _snackbarMessage.emit("Please review the marked fields")
            } else {
                _snackbarMessage.emit("Welcome to SoundIn")
            }
        }
    }
}