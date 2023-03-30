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

@HiltViewModel
class UserViewModel @Inject constructor(
    private val auth : FirebaseAuth,
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val userid = auth.currentUser?.uid
    private val _getUserData = mutableStateOf<Response<User?>>(Response.Success(null))
    val getUserData : State<Response<User?>> = _getUserData

    private val _setUserData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setUserData : State<Response<Boolean>> = _setUserData

    fun getUserInfo() {
        if(userid != null) {
            viewModelScope.launch {
                userUseCases.getUserDetails(userid = userid).collect {
                    _getUserData.value = it
                }
            }
        }
    }

    fun setUserInfo(firstName: String, lastName: String, age: String, imageUrl: String, email: String, password: String) {
        if(userid != null) {
            viewModelScope.launch {
                userUseCases.setUserDetails(
                    userid = userid,
                    firstName = firstName,
                    lastName = lastName,
                    age = age,
                    imageUrl = imageUrl,
                    email = email,
                    password = password
                ).collect {
                    _setUserData.value = it
                }
            }
        }
    }
}