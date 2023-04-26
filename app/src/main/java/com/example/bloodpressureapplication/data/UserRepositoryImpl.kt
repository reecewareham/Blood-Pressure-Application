package com.example.bloodpressureapplication.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.bloodpressureapplication.domain.model.User
import com.example.bloodpressureapplication.domain.repository.UserRepository
import com.example.bloodpressureapplication.util.Constants.COLLECTION_NAME_USERS
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth : FirebaseAuth,
    private val firebaseFirestore : FirebaseFirestore
) : UserRepository {

    private var operationSuccessful = false

    ////////////////////////////////////////////////////////////////////
    /*
    getUserDetails. Takes in the user id. Queries the User Firestore and
    finds the specific document. Takes all the information from the
    document and creates a User object. Returns the User object if
    successful.
    */
    ////////////////////////////////////////////////////////////////////

    override fun getUserDetails(userid: String): Flow<Response<User>> = callbackFlow{
        Response.Loading
        val snapShotListener = firebaseFirestore.collection(COLLECTION_NAME_USERS)
            .document(userid)
            .addSnapshotListener {snapshot, error ->
                val response = if(snapshot != null) {
                    val userInfo = snapshot.toObject(User::class.java)
                    Response.Success<User>(userInfo!!)
                } else {
                    Response.Error(error?.message?:error.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapShotListener.remove()
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    setUserDetails. Takes in all the details of the user. Queries the
    User Firestore and finds the specific document. Updates all the
    information in the document with the details passed in. Notifies
    the authentication service of a password update and email update.
    Returns true if successful.
    */
    ////////////////////////////////////////////////////////////////////

    override fun setUserDetails(userid: String, firstName: String, lastName: String, age: String, imageUrl: String, email: String, password: String, oldEmail: String, oldPassword: String) : Flow<Response<Boolean>> = flow  {

        operationSuccessful = false

        try {
            val userObj = mutableMapOf<String,String>()
            userObj["email"] = email
            userObj["password"] = password
            userObj["firstName"] = firstName
            userObj["lastName"] = lastName
            userObj["age"] = age
            userObj["imageUrl"] = imageUrl

            firebaseFirestore.collection(COLLECTION_NAME_USERS).document(userid).update(userObj as Map<String,Any>)
                .addOnSuccessListener {
                    operationSuccessful = true

                    val user = Firebase.auth.currentUser
                    val credential = EmailAuthProvider
                        .getCredential(oldEmail, oldPassword)
                    user!!.reauthenticate(credential)
                        .addOnCompleteListener {
                            Log.d(TAG, "User re-authenticated.")
                            user!!.updatePassword(password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "User password updated.")
                                        user!!.updateEmail(email)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Log.d(TAG, "User email address updated.")

                                                }
                                            }
                                    }
                                }
                        }




                }.await()
            if (operationSuccessful) {
                emit(Response.Success(operationSuccessful))
            } else {
                emit(Response.Error("Editing was not successful"))
            }
        } catch (e:Exception) {
            Response.Error(e.localizedMessage?: "An unexpected error has occurred")
        }
    }

}