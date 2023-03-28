package com.example.bloodpressureapplication.domain.repository

import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow

interface HeartRateReadingsRepository {

    fun getAllHeartReadings(userid: String) : Flow<Response<List<HeartRateReadings>>>

    fun uploadHeartReading(
        userId: String,
        bpm: Int,
        readingStatus: String,
        timestamp: Timestamp
    ) : Flow<Response<Boolean>>
}