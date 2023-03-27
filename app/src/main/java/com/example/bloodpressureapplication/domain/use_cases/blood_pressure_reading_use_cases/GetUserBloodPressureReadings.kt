package com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases

import com.example.bloodpressureapplication.domain.repository.BloodPressureReadingRepository
import com.example.bloodpressureapplication.domain.repository.UserRepository
import javax.inject.Inject

class GetUserBloodPressureReadings @Inject constructor(
    private val repository: BloodPressureReadingRepository
) {
    operator fun invoke(userid: String) = repository.getUserBloodPressureReadings(userId = userid)
}