package com.example.bloodpressureapplication.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.bloodpressureapplication.util.Response

interface AuthenticationRepository {

    fun isUserAuthenticatedInFirebase() : Boolean

    fun getFirebaseAuthState() : Flow<Boolean>

    fun firebaseSignIn(email: String, password: String) : Flow<Response<Boolean>>

    fun firebaseSignOut() : Flow<Response<Boolean>>

    fun firebaseSignUp(email: String, password: String, firstName: String, lastName: String, age: String, imageUrl: String) : Flow<Response<Boolean>>
}