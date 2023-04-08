package com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases

import com.example.bloodpressureapplication.domain.repository.BloodPressureReadingsRepository
import com.google.firebase.Timestamp
import javax.inject.Inject

class DeleteReading @Inject constructor(
    private val repository: BloodPressureReadingsRepository
) {
    operator fun invoke(bloodPressureReadingId : String) = repository.deleteReading(bloodPressureReadingId = bloodPressureReadingId)
}