package com.example.bloodpressureapplication.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloodpressureapplication.domain.model.User
import com.example.bloodpressureapplication.domain.use_cases.user_use_cases.UserUseCases
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

////////////////////////////////////////////////////////////////////
/*
User View Model. Used as the middle ground between the
backend Firestore database system and the user interface.
*/
////////////////////////////////////////////////////////////////////

@HiltViewModel
class UserViewModel @Inject constructor(
    private val auth : FirebaseAuth,
    private val userUseCases: UserUseCases
) : ViewModel() {

    ////////////////////////////////////////////////////////////////////
    /*
    States. Used to notify the user interface that a backend function has
    completed. If a function is successful, it will set the corresponding
    state to true which will notify the user interface to make a change.
    */
    ////////////////////////////////////////////////////////////////////

    private val userid = auth.currentUser?.uid
    private val _getUserData = mutableStateOf<Response<User?>>(Response.Success(null))
    val getUserData : State<Response<User?>> = _getUserData

    private val _setUserData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setUserData : State<Response<Boolean>> = _setUserData

    ////////////////////////////////////////////////////////////////////
    /*
    getUserInfo. Interacts with the getUserDetails use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets getUserData to the result which is a user object.
    The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun getUserInfo() {
        if(userid != null) {
            viewModelScope.launch {
                userUseCases.getUserDetails(userid = userid).collect {
                    _getUserData.value = it
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    setUserInfo. Interacts with the getUserDetails use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets getUserData to the result which is a user object.
    The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun setUserInfo(firstName: String, lastName: String, age: String, imageUrl: String, email: String, password: String, oldEmail: String, oldPassword: String) {
        if(userid != null) {
            viewModelScope.launch {
                userUseCases.setUserDetails(
                    userid = userid,
                    firstName = firstName,
                    lastName = lastName,
                    age = age,
                    imageUrl = imageUrl,
                    email = email,
                    password = password,
                    oldEmail = oldEmail,
                    oldPassword = oldPassword
                ).collect {
                    _setUserData.value = it
                }
            }
        }
    }
}