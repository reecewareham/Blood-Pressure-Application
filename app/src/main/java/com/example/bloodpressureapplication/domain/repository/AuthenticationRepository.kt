package com.example.bloodpressureapplication.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.bloodpressureapplication.util.Response

////////////////////////////////////////////////////////////////////
/*
Authentication Repository. An interface that creates the functions
needed for interacting with the Firebase authentication system.
*/
////////////////////////////////////////////////////////////////////

interface AuthenticationRepository {

    fun isUserAuthenticatedInFirebase() : Boolean

    fun getFirebaseAuthState() : Flow<Boolean>

    fun firebaseSignIn(email: String, password: String) : Flow<Response<Boolean>>

    fun firebaseSignOut() : Flow<Response<Boolean>>

    fun firebaseSignUp(email: String, password: String, firstName: String, lastName: String, age: String, imageUrl: String) : Flow<Response<Boolean>>
}