package com.example.soundinch9.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _birthDate = MutableStateFlow("")
    val birthDate: StateFlow<String> = _birthDate.asStateFlow()

    private val _favoriteGenre = MutableStateFlow("")
    val favoriteGenre: StateFlow<String> = _favoriteGenre.asStateFlow()

    private val _acceptedTerms = MutableStateFlow(false)
    val acceptedTerms: StateFlow<Boolean> = _acceptedTerms.asStateFlow()

    private val _nameError = MutableStateFlow(false)
    val nameError: StateFlow<Boolean> = _nameError.asStateFlow()

    private val _emailError = MutableStateFlow(false)
    val emailError: StateFlow<Boolean> = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow(false)
    val passwordError: StateFlow<Boolean> = _passwordError.asStateFlow()

    private val _confirmPasswordError = MutableStateFlow(false)
    val confirmPasswordError: StateFlow<Boolean> = _confirmPasswordError.asStateFlow()

    private val _birthDateError = MutableStateFlow(false)
    val birthDateError: StateFlow<Boolean> = _birthDateError.asStateFlow()

    private val _genreError = MutableStateFlow(false)
    val genreError: StateFlow<Boolean> = _genreError.asStateFlow()

    private val _termsError = MutableStateFlow(false)
    val termsError: StateFlow<Boolean> = _termsError.asStateFlow()

    // One-off events shouldn't be regular state (state replays its last value to
    // every new collector, e.g. after rotation, which would re-show a stale
    // snackbar). A SharedFlow with no replay avoids that - same pattern as LoginViewModel.
    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage: SharedFlow<String> = _snackbarMessage

    private val _registrationComplete = MutableStateFlow(false)
    val registrationComplete: StateFlow<Boolean> = _registrationComplete.asStateFlow()

    fun onNameChange(value: String) {
        _name.value = value
        _nameError.value = false
    }

    fun onEmailChange(value: String) {
        _email.value = value
        _emailError.value = false
    }

    fun onPasswordChange(value: String) {
        _password.value = value
        _passwordError.value = false
    }

    fun onConfirmPasswordChange(value: String) {
        _confirmPassword.value = value
        _confirmPasswordError.value = false
    }

    fun onBirthDateChange(value: String) {
        _birthDate.value = value
        _birthDateError.value = false
    }

    fun onGenreChange(value: String) {
        _favoriteGenre.value = value
        _genreError.value = false
    }

    fun onAcceptedTermsChange(value: Boolean) {
        _acceptedTerms.value = value
        _termsError.value = false
    }

    /**
     * Used by the BackHandler to decide whether the system back button should be
     * intercepted at all. If nothing has been entered, there's nothing to lose,
     * so back navigation should happen immediately with no confirmation dialog.
     */
    fun isFormEmpty(): Boolean {
        return _name.value.isBlank() &&
                _email.value.isBlank() &&
                _password.value.isBlank() &&
                _confirmPassword.value.isBlank() &&
                _birthDate.value.isBlank() &&
                _favoriteGenre.value.isBlank()
    }

    fun validateAndRegister() {
        val isNameValid = _name.value.isNotBlank()
        val isEmailValid = _email.value.contains('@') && _email.value.contains('.')
        val isPasswordValid = _password.value.length >= 8
        val doPasswordsMatch = _confirmPassword.value.isNotBlank() &&
                _password.value == _confirmPassword.value
        val isBirthDateValid = _birthDate.value.isNotBlank()
        val isGenreValid = _favoriteGenre.value.isNotBlank()
        val areTermsAccepted = _acceptedTerms.value

        _nameError.value = !isNameValid
        _emailError.value = !isEmailValid
        _passwordError.value = !isPasswordValid
        _confirmPasswordError.value = !doPasswordsMatch
        _birthDateError.value = !isBirthDateValid
        _genreError.value = !isGenreValid
        _termsError.value = !areTermsAccepted

        val isFormValid = isNameValid && isEmailValid && isPasswordValid &&
                doPasswordsMatch && isBirthDateValid && isGenreValid && areTermsAccepted

        viewModelScope.launch {
            if (isFormValid) {
                _registrationComplete.value = true
                _snackbarMessage.emit("Account created successfully")
            } else {
                _snackbarMessage.emit("Invalid data")
            }
        }
    }
}