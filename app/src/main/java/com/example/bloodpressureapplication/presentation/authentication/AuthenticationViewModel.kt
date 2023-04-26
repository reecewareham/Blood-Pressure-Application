package com.example.bloodpressureapplication.presentation.authentication

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloodpressureapplication.domain.use_cases.authentication_use_cases.AuthenticationUseCases
import com.example.bloodpressureapplication.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

////////////////////////////////////////////////////////////////////
/*
Authentication View Model. Used as the middle ground between the
backend Firebase authentication systems and the user interface.
*/
////////////////////////////////////////////////////////////////////

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private var authUseCases : AuthenticationUseCases
) : ViewModel() {

    val isUserAuthenticated get() = authUseCases.isUserAuthenticated()

    ////////////////////////////////////////////////////////////////////
    /*
    States. Used to notify the user interface that a backend function has
    completed. If a function is successful, it will set the corresponding
    state to true which will notify the user interface to make a change.
    */
    ////////////////////////////////////////////////////////////////////

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState : State<Response<Boolean>> = _signInState

    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState : State<Response<Boolean>> = _signUpState

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState : State<Response<Boolean>> = _signOutState

    private val _firebaseAuthState = mutableStateOf<Boolean>(false)
    val firebaseAuthState : State<Boolean> = _firebaseAuthState

    ////////////////////////////////////////////////////////////////////
    /*
    SignOut. Interacts with the firebaseSignOut use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets signOutState to the result. If the result is true,
    set the sign in state to false resulting in the user being directed
    to the login screen.
    */
    ////////////////////////////////////////////////////////////////////

    fun signOut() {
        viewModelScope.launch {
            authUseCases.firebaseSignOut().collect {
                _signOutState.value = it
                if (it == Response.Success(true)) {
                    _signInState.value = Response.Success(false)
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    SignIn. Takes an email and password. Interacts with the firebaseSignIn
    use case which runs the corresponding implementation function. Collects
    the result of the function and sets signInState to that value. The result
    is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.firebaseSignIn(email = email, password = password).collect {
                _signInState.value = it
            }
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    SignUp. Takes all the user details. Interacts with the firebaseSignUp
    use case which runs the corresponding implementation function. Collects
    the result of the function and sets signUpState to that value. The result
    is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun signUp(email: String, password: String, firstName: String, lastName: String, age: String, imageUrl: String) {
        viewModelScope.launch {
            authUseCases.firebaseSignUp(email = email, password = password, firstName = firstName, lastName = lastName, age = age, imageUrl = imageUrl)
                .collect {
                    _signUpState.value = it
                }
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    getFirebaseAuthState. Interacts with the firebaseAuthState use case
    which runs the corresponding implementation function. Collects
    the result of the function and sets firebaseAuthState to that value.
    The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun getFirebaseAuthState() {
        viewModelScope.launch {
            authUseCases.firebaseAuthState().collect {
                _firebaseAuthState.value = it
            }
        }
    }


}