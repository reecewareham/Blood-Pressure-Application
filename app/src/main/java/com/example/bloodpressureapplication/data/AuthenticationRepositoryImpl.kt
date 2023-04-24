package com.example.bloodpressureapplication.data

import android.util.Log
import com.example.bloodpressureapplication.domain.model.User
import com.example.bloodpressureapplication.domain.repository.AuthenticationRepository
import com.example.bloodpressureapplication.util.Constants
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CompletableDeferred
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
        try {
            emit(Response.Loading)
            val isSuccessfulDeferred = CompletableDeferred<Boolean>()
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                isSuccessfulDeferred.complete(true)

            }.addOnFailureListener {
                isSuccessfulDeferred.complete(false)
            }
            val isSuccessful = isSuccessfulDeferred.await()
            emit(Response.Success(isSuccessful))
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
        try {
            emit(Response.Loading)
            val isAuthSuccessfulDeferred = CompletableDeferred<Boolean>()
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                isAuthSuccessfulDeferred.complete(true)
            }.addOnFailureListener {
                isAuthSuccessfulDeferred.complete(false)
            }
            val isAuthSuccessful = isAuthSuccessfulDeferred.await()

            if (isAuthSuccessful) {
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
                val isFirestoreSuccessfulDeferred = CompletableDeferred<Boolean>()
                firestore.collection(Constants.COLLECTION_NAME_USERS).document(userid).set(obj).addOnSuccessListener {
                    isFirestoreSuccessfulDeferred.complete(true)
                }.addOnFailureListener {
                    isFirestoreSuccessfulDeferred.complete(false)
                }
                val isFirestoreSuccessful = isFirestoreSuccessfulDeferred.await()

                emit(Response.Success(isFirestoreSuccessful))
            } else {
                Response.Success(false)
            }
        } catch (e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An unexpected error"))
        }
    }
}