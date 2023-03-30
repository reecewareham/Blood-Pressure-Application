package com.example.bloodpressureapplication.domain.repository

import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow

interface BloodPressureReadingsRepository {

    fun getAllReadings(userid: String) : Flow<Response<List<BloodPressureReadings>>>

    fun getLast5Readings(userid: String) : Flow<Response<List<BloodPressureReadings>>>

    fun uploadReading(
        userId: String,
        systolicPressure: Int,
        diastolicPressure: Int,
        timestamp: Timestamp
    ) : Flow<Response<Boolean>>
}