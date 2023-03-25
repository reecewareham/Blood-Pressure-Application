package com.example.bloodpressureapplication.data

import com.example.bloodpressureapplication.domain.model.User
import com.example.bloodpressureapplication.domain.repository.AuthenticationRepository
import com.example.bloodpressureapplication.util.Constants
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth : FirebaseAuth,
    private val firestore : FirebaseFirestore
) : AuthenticationRepository {

    var operationSuccessful : Boolean = false

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            emit(Response.Success(operationSuccessful))
        } catch (e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An unexpected error"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow{
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        } catch (e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An unexpected error"))
        }
    }

    override fun firebaseSignUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        age: String,
        imageUrl: String
    ): Flow<Response<Boolean>> = flow{
        operationSuccessful = false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            if (operationSuccessful) {
                val userid = auth.currentUser?.uid!!
                val obj = User(
                    userId = userid,
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    age = age,
                    imageUrl = imageUrl
                )
                firestore.collection(Constants.COLLECTION_NAME_USERS).document(userid).set(obj).addOnSuccessListener {

                }.await()
                emit(Response.Success(operationSuccessful))
            } else {
                Response.Success(operationSuccessful)
            }
        } catch (e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An unexpected error"))
        }
    }
}