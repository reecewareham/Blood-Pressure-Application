package com.example.bloodpressureapplication.domain.use_cases.heart_rate_reading_use_cases

import com.example.bloodpressureapplication.domain.repository.HeartRateReadingsRepository
import javax.inject.Inject

class GetHeartReading @Inject constructor(
    private val repository: HeartRateReadingsRepository
){
    operator fun invoke(heartRateReadingId: String) = repository.getHeartReading(heartRateReadingId = heartRateReadingId)
}