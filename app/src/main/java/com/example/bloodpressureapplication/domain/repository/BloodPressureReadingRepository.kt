package com.example.bloodpressureapplication.domain.repository

import com.example.bloodpressureapplication.domain.model.BloodPressureReading
import com.example.bloodpressureapplication.util.Response
import kotlinx.coroutines.flow.Flow

interface BloodPressureReadingRepository {

    fun getUserBloodPressureReadings(userId: String) : Flow<Response<BloodPressureReading>>
}