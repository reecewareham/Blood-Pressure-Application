package com.example.bloodpressureapplication.data

import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.domain.repository.HeartRateReadingsRepository
import com.example.bloodpressureapplication.util.Constants
import com.example.bloodpressureapplication.util.Constants.COLLECTION_NAME_HEART_RATE_READINGS
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HeartRateReadingsRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : HeartRateReadingsRepository {

    private var operationSuccessful = false

    override fun getAllHeartReadings(userid: String): Flow<Response<List<HeartRateReadings>>> = callbackFlow {

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .whereEqualTo("userId", userid)
            .addSnapshotListener {snapshot, e->
                val response = if (snapshot != null) {
                    val heartRateReadingsList = snapshot.toObjects(HeartRateReadings::class.java)
                    Response.Success<List<HeartRateReadings>>(heartRateReadingsList)
                } else {
                    Response.Error(e?.message?:e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getLastHeartReading(userid: String): Flow<Response<List<HeartRateReadings>>> = callbackFlow{

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(Constants.COLLECTION_NAME_HEART_RATE_READINGS)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .whereEqualTo("userId", userid)
            .limit(1)
            .addSnapshotListener {snapshot, e->
                val response = if (snapshot != null) {
                    val heartRateReadingsList = snapshot.toObjects(HeartRateReadings::class.java)
                    Response.Success<List<HeartRateReadings>>(heartRateReadingsList)
                } else {
                    Response.Error(e?.message?:e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getLast5HeartReadings(userid: String): Flow<Response<List<HeartRateReadings>>> = callbackFlow{

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(Constants.COLLECTION_NAME_HEART_RATE_READINGS)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .whereEqualTo("userId", userid)
            .limit(5)
            .addSnapshotListener {snapshot, e->
                val response = if (snapshot != null) {
                    val heartRateReadingsList = snapshot.toObjects(HeartRateReadings::class.java)
                    Response.Success<List<HeartRateReadings>>(heartRateReadingsList)
                } else {
                    Response.Error(e?.message?:e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }

    }

    override fun getHeartReading(heartRateReadingId: String): Flow<Response<HeartRateReadings>> = callbackFlow {

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS)
            .document(heartRateReadingId)
            .addSnapshotListener {snapshot, e->
                val response = if (snapshot != null) {
                    val heartRateReading = snapshot.toObject(HeartRateReadings::class.java)
                    Response.Success<HeartRateReadings>(heartRateReading!!)
                } else {
                    Response.Error(e?.message?:e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun uploadHeartReading(
        userId: String,
        bpm: Int,
        readingStatus: String,
        timestamp: Timestamp
    ): Flow<Response<Boolean>> = flow {

        operationSuccessful = false
        try{
            val heartRateReadingId = firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS).document().id
            val heartRateReading = HeartRateReadings (
                heartRateReadingId = heartRateReadingId,
                userId = userId,
                bpm = bpm,
                readingStatus = readingStatus,
                timestamp = timestamp
            )
            firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS).document(heartRateReadingId).set(heartRateReading).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            if (operationSuccessful) {
                emit(Response.Success(operationSuccessful))
            } else {
                emit(Response.Error("Upload unsuccessful"))
            }
        } catch(e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An unexpected error has occurred"))
        }
    }

    override fun updateHeartReading(
        heartRateReadingId: String,
        bpm: Int,
        readingStatus: String
    ): Flow<Response<Boolean>> = flow {

        operationSuccessful = false

        try {
            val readingObj = mutableMapOf<String,Any>()
            readingObj["bpm"] = bpm
            readingObj["readingStatus"] = readingStatus


            firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS).document(heartRateReadingId).update(readingObj as Map<String,Any>)
                .addOnSuccessListener {
                    operationSuccessful = true
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

    override fun deleteHeartReading(heartRateReadingId: String) : Flow<Response<Boolean>> = flow {

        operationSuccessful = false

        try {
            firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS).document(heartRateReadingId).delete()
                .addOnSuccessListener {
                    operationSuccessful = true
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