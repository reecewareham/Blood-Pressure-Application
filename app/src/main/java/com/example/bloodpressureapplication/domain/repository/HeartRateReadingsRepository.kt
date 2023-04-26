package com.example.bloodpressureapplication.domain.repository

import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow

////////////////////////////////////////////////////////////////////
/*
Heart Rate Readings Repository. An interface that creates the
functions needed for interacting with the heart rate reading
Firestore.
*/
////////////////////////////////////////////////////////////////////

interface HeartRateReadingsRepository {

    fun getAllHeartReadings(userid: String) : Flow<Response<List<HeartRateReadings>>>

    fun getLastHeartReading(userid: String) : Flow<Response<List<HeartRateReadings>>>

    fun getLast5HeartReadings(userid: String) : Flow<Response<List<HeartRateReadings>>>

    fun getHeartReading(heartRateReadingId: String) : Flow<Response<HeartRateReadings>>

    fun uploadHeartReading(
        userId: String,
        bpm: Int,
        readingStatus: String,
        timestamp: Timestamp
    ) : Flow<Response<Boolean>>

    fun updateHeartReading(
        heartRateReadingId: String,
        bpm: Int,
        readingStatus: String
    ) : Flow<Response<Boolean>>

    fun deleteHeartReading(
        heartRateReadingId: String
    ) : Flow<Response<Boolean>>
}