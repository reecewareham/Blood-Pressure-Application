package com.example.bloodpressureapplication.domain.use_cases.heart_rate_reading_use_cases

import com.example.bloodpressureapplication.domain.repository.HeartRateReadingsRepository
import javax.inject.Inject

class GetLastHeartReading @Inject constructor(
    private val repository: HeartRateReadingsRepository
){
    operator fun invoke(userid: String) = repository.getLastHeartReading(userid = userid)
}