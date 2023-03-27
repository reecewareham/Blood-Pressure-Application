package com.example.bloodpressureapplication.domain.model

import com.google.firebase.Timestamp

data class BloodPressureReading(
    var userId: String = "",
    var SystolicPressure: Int = 0,
    var DiastolicPressure: Int = 0,
    var Timestamp: Timestamp
)
