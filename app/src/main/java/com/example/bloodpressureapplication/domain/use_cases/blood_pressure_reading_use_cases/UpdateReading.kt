package com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases

import com.example.bloodpressureapplication.domain.repository.BloodPressureReadingsRepository
import javax.inject.Inject

class UpdateReading @Inject constructor(
    private val repository: BloodPressureReadingsRepository
) {
    operator fun invoke(bloodPressureReadingId : String, systolicPressure : Int, diastolicPressure : Int) = repository.updateReading(bloodPressureReadingId = bloodPressureReadingId, systolicPressure = systolicPressure, diastolicPressure = diastolicPressure)
}