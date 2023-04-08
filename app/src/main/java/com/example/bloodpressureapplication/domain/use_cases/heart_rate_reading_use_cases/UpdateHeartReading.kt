package com.example.bloodpressureapplication.domain.use_cases.heart_rate_reading_use_cases

import com.example.bloodpressureapplication.domain.repository.HeartRateReadingsRepository
import javax.inject.Inject

class UpdateHeartReading @Inject constructor(
    private val repository: HeartRateReadingsRepository
) {
    operator fun invoke(heartRateReadingId : String, bpm : Int, readingStatus : String) = repository.updateHeartReading(heartRateReadingId = heartRateReadingId, bpm = bpm, readingStatus = readingStatus)
}