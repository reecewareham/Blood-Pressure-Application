package com.example.bloodpressureapplication.domain.model

import com.google.firebase.Timestamp

data class HeartRateReadings(
    var heartRateReadingId : String = "",
    var userId : String = "",
    var bpm : Int = 0,
    var readingStatus : String = "",
    var timestamp : Timestamp? = null
)
