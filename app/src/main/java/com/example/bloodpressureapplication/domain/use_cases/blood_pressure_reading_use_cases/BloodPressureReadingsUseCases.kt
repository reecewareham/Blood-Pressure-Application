package com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases

data class BloodPressureReadingsUseCases(
    var getAllReadings: GetAllReadings,
    var uploadReading: UploadReading,
    var getLast5Readings: GetLast5Readings,
    var getLastReading: GetLastReading,
    var deleteReading: DeleteReading,
    var updateReading: UpdateReading,
    var getReading: GetReading
)
