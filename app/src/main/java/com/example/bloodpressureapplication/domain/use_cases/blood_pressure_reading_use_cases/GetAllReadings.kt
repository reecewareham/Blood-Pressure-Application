package com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases

import com.example.bloodpressureapplication.domain.repository.BloodPressureReadingsRepository
import javax.inject.Inject

class GetAllReadings @Inject constructor(
    private val repository: BloodPressureReadingsRepository
){
    operator fun invoke(userid: String) = repository.getAllReadings(userid = userid)
}