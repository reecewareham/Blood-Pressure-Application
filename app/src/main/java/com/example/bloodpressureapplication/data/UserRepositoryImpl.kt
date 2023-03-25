package com.example.bloodpressureapplication.data

import com.example.bloodpressureapplication.domain.model.User
import com.example.bloodpressureapplication.domain.repository.UserRepository
import com.example.bloodpressureapplication.util.Constants.COLLECTION_NAME_USERS
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore : FirebaseFirestore
) : UserRepository {

    private var operationSuccessful = false

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

    override fun setUserDetails(userid: String, firstName: String, lastName: String, age: String, imageUrl: String) : Flow<Response<Boolean>> = flow  {

        operationSuccessful = false

        try {
            val userObj = mutableMapOf<String,String>()
            userObj["firstName"] = firstName
            userObj["lastName"] = lastName
            userObj["age"] = age
            userObj["imageUrl"] = imageUrl

            firebaseFirestore.collection(COLLECTION_NAME_USERS).document(userid).update(userObj as Map<String,Any>)
                .addOnSuccessListener {

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