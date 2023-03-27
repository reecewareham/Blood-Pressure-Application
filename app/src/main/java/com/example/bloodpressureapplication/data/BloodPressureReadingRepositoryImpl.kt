package com.example.bloodpressureapplication.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.bloodpressureapplication.domain.model.BloodPressureReading
import com.example.bloodpressureapplication.domain.model.User
import com.example.bloodpressureapplication.domain.repository.BloodPressureReadingRepository
import com.example.bloodpressureapplication.util.Constants.COLLECTION_NAME_BLOOD_PRESSURE_READINGS
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BloodPressureReadingRepositoryImpl @Inject constructor(
    private val firebaseFirestore : FirebaseFirestore
) : BloodPressureReadingRepository {

    override fun getUserBloodPressureReadings(userId: String): Flow<Response<BloodPressureReading>> = callbackFlow {

        Response.Loading
        firebaseFirestore.collection(COLLECTION_NAME_BLOOD_PRESSURE_READINGS)
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }
}