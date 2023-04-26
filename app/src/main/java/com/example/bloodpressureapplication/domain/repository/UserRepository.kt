package com.example.bloodpressureapplication.domain.repository

import com.example.bloodpressureapplication.domain.model.User
import com.example.bloodpressureapplication.util.Response
import kotlinx.coroutines.flow.Flow

////////////////////////////////////////////////////////////////////
/*
User Repository. An interface that creates the functions needed for
interacting with the user Firestore.
*/
////////////////////////////////////////////////////////////////////

interface UserRepository {

    fun getUserDetails(userid: String) : Flow<Response<User>>

    fun setUserDetails(userid: String, firstName: String, lastName: String, age: String, imageUrl: String, email: String, password: String, oldEmail: String, oldPassword: String) : Flow<Response<Boolean>>

}