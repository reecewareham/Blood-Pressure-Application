package com.example.bloodpressureapplication.data

import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.domain.repository.HeartRateReadingsRepository
import com.example.bloodpressureapplication.util.Constants
import com.example.bloodpressureapplication.util.Constants.COLLECTION_NAME_HEART_RATE_READINGS
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

////////////////////////////////////////////////////////////////////
/*
Blood Pressure Readings Repository Implementation. Implements the functions
used to access the blood pressure readings Firestore.
*/
////////////////////////////////////////////////////////////////////

class HeartRateReadingsRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : HeartRateReadingsRepository {

    private var operationSuccessful = false

    ////////////////////////////////////////////////////////////////////
    /*
    getAllHeartReadings. Takes in the current user id. Queries the heart
    rate readings Firestore using the user id and orders the readings
    by timestamp. Returns a list of all readings if successful.
    */
    ////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////
    /*
    getLastHeartReading. Takes in the current user id. Queries the heart
    rate readings Firestore using the user id and orders the readings
    by timestamp. Returns the last reading that was entered.
    */
    ////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////
    /*
    getLast5HeartReadings. Takes in the current user id. Queries the heart
    rate readings Firestore using the user id and orders the readings
    by timestamp. Returns the last 5 readings in a list.
    */
    ////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////
    /*
    getHeartReading. Takes in a heart rate reading id. Queries the heart
    rate readings Firestore using the id and finds the correct document.
    Returns the specified reading if successful.
    */
    ////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////
    /*
    uploadHeartReading. Takes in a user id, bpm reading, reading status
    and timestamp. Creates a new unique document in the Firestore. Adds
    the rest of the details to the document once it is created. Returns
    true if successful.
    */
    ////////////////////////////////////////////////////////////////////

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
            val isSuccessfulDeferred = CompletableDeferred<Boolean>()
            firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS).document(heartRateReadingId).set(heartRateReading).addOnSuccessListener {
                isSuccessfulDeferred.complete(true)

            }.addOnFailureListener {
                isSuccessfulDeferred.complete(false)
            }
            val isSuccessful = isSuccessfulDeferred.await()
            if (isSuccessful) {
                emit(Response.Success(isSuccessful))
            } else {
                emit(Response.Error("Upload unsuccessful"))
            }
        } catch(e:Exception) {
            emit(Response.Error(e.localizedMessage?:"An unexpected error has occurred"))
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    updateHeartReading. Takes in the heart rate reading id, bpm reading
    and reading status. Queries the Firestore using the id and finds the
    specified document. Updates the bpm reading and reading status
    with the new values. Returns true if successful.
    */
    ////////////////////////////////////////////////////////////////////

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

            val isSuccessfulDeferred = CompletableDeferred<Boolean>()
            firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS).document(heartRateReadingId).update(readingObj as Map<String,Any>)
                .addOnSuccessListener {
                    isSuccessfulDeferred.complete(true)

                }.addOnFailureListener {
                    isSuccessfulDeferred.complete(false)
                }
            val isSuccessful = isSuccessfulDeferred.await()
            if (isSuccessful) {
                emit(Response.Success(isSuccessful))
            } else {
                emit(Response.Error("Editing was not successful"))
            }
        } catch (e:Exception) {
            Response.Error(e.localizedMessage?: "An unexpected error has occurred")
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    deleteHeartReading. Takes in the heart rate reading id. Queries the
    Firestore and finds the specific document. Deletes it once found.
    Returns true if successful.
    */
    ////////////////////////////////////////////////////////////////////

    override fun deleteHeartReading(heartRateReadingId: String) : Flow<Response<Boolean>> = flow {

        operationSuccessful = false

        try {
            val isSuccessfulDeferred = CompletableDeferred<Boolean>()
            firebaseFirestore.collection(COLLECTION_NAME_HEART_RATE_READINGS).document(heartRateReadingId).delete()
                .addOnSuccessListener {
                    isSuccessfulDeferred.complete(true)

                }.addOnFailureListener {
                    isSuccessfulDeferred.complete(false)
                }
            val isSuccessful = isSuccessfulDeferred.await()
            if (isSuccessful) {
                emit(Response.Success(isSuccessful))
            } else {
                emit(Response.Error("Delete was not successful"))
            }
        } catch (e:Exception) {
            Response.Error(e.localizedMessage?: "An unexpected error has occurred")
        }
    }
}