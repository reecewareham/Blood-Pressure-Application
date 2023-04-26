package com.example.bloodpressureapplication.domain.model

import com.google.firebase.Timestamp

////////////////////////////////////////////////////////////////////
/*
Blood Pressure Readings Model. Specifies the model used for blood
pressure readings.
*/
////////////////////////////////////////////////////////////////////

data class BloodPressureReadings(
    var bloodPressureReadingId : String = "",
    var userId : String = "",
    var systolicPressure : Int = 0,
    var diastolicPressure : Int = 0,
    var timestamp : Timestamp? = null

) {
    constructor(): this("", "", 0, 0, )
}
