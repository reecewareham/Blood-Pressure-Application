package com.example.bloodpressureapplication.login

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloodpressureapplication.repository.AuthRepository
import kotlinx.coroutines.launch
import kotlin.math.log

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    val currentUser = repository.currentUser

    val hasUser:Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(email: String) {
        loginUiState = loginUiState.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }

    fun onEmailChangeSignUp(email: String) {
        loginUiState = loginUiState.copy(emailSignUp = email)
    }

    fun onPasswordChangeSignUp(password: String) {
        loginUiState = loginUiState.copy(passwordSignUp = password)
    }

    fun onConfirmPasswordChange(password: String) {
        loginUiState = loginUiState.copy(confirmPasswordSignUp = password)
    }

    private fun validateLoginForm() =
        loginUiState.email.isNotBlank() &&
                loginUiState.password.isNotBlank()

    private fun validateSignUpForm() =
        loginUiState.emailSignUp.isNotBlank() &&
                loginUiState.passwordSignUp.isNotBlank() &&
                loginUiState.confirmPasswordSignUp.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignUpForm()) {
                throw IllegalArgumentException("Email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            if (loginUiState.passwordSignUp != loginUiState.confirmPasswordSignUp) {
                throw IllegalArgumentException("Passwords do not match")
            }
            loginUiState = loginUiState.copy(signUpError = null)
            repository.createUser(
                loginUiState.emailSignUp,
                loginUiState.passwordSignUp
            ) {
                isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(context, "Success Login",Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(context, "Failed Login",Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        } catch (e:Exception) {
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateLoginForm()) {
                throw IllegalArgumentException("Email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.email,
                loginUiState.password
            ) {
                    isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(context, "Success Login",Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(context, "Failed Login",Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        } catch (e:Exception) {
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }
}

data class LoginUiState(
    val email:String = "",
    val password:String = "",
    val emailSignUp:String = "",
    val passwordSignUp:String = "",
    val confirmPasswordSignUp:String = "",
    val isLoading:Boolean = false,
    val isSuccessLogin:Boolean = false,
    val signUpError:String? = null,
    val loginError:String? = null
)