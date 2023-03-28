package com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases

data class BloodPressureReadingsUseCases(
    var getAllReadings: GetAllReadings,
    var uploadReading: UploadReading
)
