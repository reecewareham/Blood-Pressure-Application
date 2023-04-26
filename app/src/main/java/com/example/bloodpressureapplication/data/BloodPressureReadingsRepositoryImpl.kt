package com.example.bloodpressureapplication.data

import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.repository.BloodPressureReadingsRepository
import com.example.bloodpressureapplication.util.Constants.COLLECTION_NAME_BLOOD_PRESSURE_READINGS
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

class BloodPressureReadingsRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : BloodPressureReadingsRepository {

    private var operationSuccessful = false

    ////////////////////////////////////////////////////////////////////
    /*
    getAllReadings. Takes in the current user id. Queries the blood
    pressure readings Firestore using the user id and orders the readings
    by timestamp. Returns a list of all readings if successful.
    */
    ////////////////////////////////////////////////////////////////////

    override fun getAllReadings(userid: String): Flow<Response<List<BloodPressureReadings>>> = callbackFlow{

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .whereEqualTo("userId", userid)
            .addSnapshotListener {snapshot, e->
                val response = if (snapshot != null) {
                    val bloodPressureReadingsList = snapshot.toObjects(BloodPressureReadings::class.java)
                    Response.Success<List<BloodPressureReadings>>(bloodPressureReadingsList)
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
    getLastReading. Takes in the current user id. Queries the blood
    pressure readings Firestore using the user id and orders the readings
    by timestamp. Returns the last reading that was entered.
    */
    ////////////////////////////////////////////////////////////////////

    override fun getLastReading(userid: String): Flow<Response<List<BloodPressureReadings>>> = callbackFlow {

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .whereEqualTo("userId", userid)
            .limit(1)
            .addSnapshotListener {snapshot, e->
                val response = if (snapshot != null) {
                    val bloodPressureReadingsList = snapshot.toObjects(BloodPressureReadings::class.java)
                    Response.Success<List<BloodPressureReadings>>(bloodPressureReadingsList)
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
    getLast5Readings. Takes in the current user id. Queries the blood
    pressure readings Firestore using the user id and orders the readings
    by timestamp. Returns the last 5 readings in a list.
    */
    ////////////////////////////////////////////////////////////////////

    override fun getLast5Readings(userid: String): Flow<Response<List<BloodPressureReadings>>> = callbackFlow{

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .whereEqualTo("userId", userid)
            .limit(5)
            .addSnapshotListener {snapshot, e->
                val response = if (snapshot != null) {
                    val bloodPressureReadingsList = snapshot.toObjects(BloodPressureReadings::class.java)
                    Response.Success<List<BloodPressureReadings>>(bloodPressureReadingsList)
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
    getReading. Takes in a blood pressure reading id. Queries the blood
    pressure readings Firestore using the id and finds the correct document.
    Returns the specified reading if successful.
    */
    ////////////////////////////////////////////////////////////////////

    override fun getReading(bloodPressureReadingId: String): Flow<Response<BloodPressureReadings>> = callbackFlow{

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS)
            .document(bloodPressureReadingId)
            .addSnapshotListener {snapshot, e->
                val response = if (snapshot != null) {
                    val bloodPressureReading = snapshot.toObject(BloodPressureReadings::class.java)
                    Response.Success<BloodPressureReadings>(bloodPressureReading!!)
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
    uploadReading. Takes in a user id, systolic pressure reading, diastolic
    pressure reading and timestamp. Creates a new unique document in the
    Firestore. Adds the rest of the details to the document once it is
    created. Returns true if successful.
    */
    ////////////////////////////////////////////////////////////////////

    override fun uploadReading(
        userId: String,
        systolicPressure: Int,
        diastolicPressure: Int,
        timestamp: Timestamp
    ): Flow<Response<Boolean>> = flow {
        operationSuccessful = false
        try{
            val bloodPressureReadingId = firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS).document().id
            val bloodPressureReading = BloodPressureReadings (
                bloodPressureReadingId = bloodPressureReadingId,
                userId = userId,
                systolicPressure = systolicPressure,
                diastolicPressure = diastolicPressure,
                timestamp = timestamp
            )
            val isSuccessfulDeferred = CompletableDeferred<Boolean>()
            firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS).document(bloodPressureReadingId).set(bloodPressureReading).addOnSuccessListener {
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
    updateReading. Takes in the blood pressure reading id, systolic pressure
    and diastolic pressure. Queries the Firestore using the id and finds the
    specified document. Updates the systolic pressure and diastolic pressure
    with the new values. Returns true if successful.
    */
    ////////////////////////////////////////////////////////////////////

    override fun updateReading(
        bloodPressureReadingId: String,
        systolicPressure: Int,
        diastolicPressure: Int
    ): Flow<Response<Boolean>> = flow {

        operationSuccessful = false

        try {
            val readingObj = mutableMapOf<String,Int>()
            readingObj["systolicPressure"] = systolicPressure
            readingObj["diastolicPressure"] = diastolicPressure

            val isSuccessfulDeferred = CompletableDeferred<Boolean>()
            firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS).document(bloodPressureReadingId).update(readingObj as Map<String,Any>)
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
    deleteReading. Takes in the blood pressure reading id. Queries the
    Firestore and finds the specific document. Deletes it once found.
    Returns true if successful.
    */
    ////////////////////////////////////////////////////////////////////

    override fun deleteReading(bloodPressureReadingId: String) : Flow<Response<Boolean>> = flow {

        operationSuccessful = false

        try {
            val isSuccessfulDeferred = CompletableDeferred<Boolean>()
            firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS).document(bloodPressureReadingId).delete()
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