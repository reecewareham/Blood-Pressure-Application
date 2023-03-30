package com.example.bloodpressureapplication.data

import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.repository.BloodPressureReadingsRepository
import com.example.bloodpressureapplication.util.Constants.COLLECTION_NAME_BLOOD_PRESSURE_READINGS
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

class BloodPressureReadingsRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : BloodPressureReadingsRepository {

    private var operationSuccessful = false

    override fun getAllReadings(userid: String): Flow<Response<List<BloodPressureReadings>>> = callbackFlow{

        Response.Loading
        val snapshotListener = firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS)
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
            firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS).document(bloodPressureReadingId).set(bloodPressureReading).addOnSuccessListener {
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

}