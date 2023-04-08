package com.example.bloodpressureapplication.domain.use_cases.heart_rate_reading_use_cases

import com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases.GetAllReadings
import com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases.UploadReading

data class HeartRateReadingsUseCases(
    var getAllHeartReadings: GetAllHeartReadings,
    var uploadHeartReading: UploadHeartReading,
    var getLast5HeartReadings: GetLast5HeartReadings,
    var getLastHeartReading: GetLastHeartReading,
    var updateHeartReading: UpdateHeartReading,
    var deleteHeartReading: DeleteHeartReading,
    var getHeartReading: GetHeartReading
)
