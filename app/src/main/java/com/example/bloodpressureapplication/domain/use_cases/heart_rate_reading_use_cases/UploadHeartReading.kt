package com.example.bloodpressureapplication.domain.use_cases.heart_rate_reading_use_cases

import com.example.bloodpressureapplication.domain.repository.HeartRateReadingsRepository
import com.google.firebase.Timestamp
import javax.inject.Inject

class UploadHeartReading @Inject constructor(
    private val repository: HeartRateReadingsRepository
) {
    operator fun invoke(userid: String, bpm: Int, readingStatus: String, timestamp: Timestamp) = repository.uploadHeartReading(userId = userid, bpm = bpm, readingStatus = readingStatus, timestamp = timestamp)
}