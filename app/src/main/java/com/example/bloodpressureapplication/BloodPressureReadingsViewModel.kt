package com.example.bloodpressureapplication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.bloodpressureapplication.domain.model.BloodPressureReading
import com.example.bloodpressureapplication.domain.model.User
import com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases.BloodPressureReadingUseCases
import com.example.bloodpressureapplication.domain.use_cases.user_use_cases.UserUseCases
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BloodPressureReadingsViewModel @Inject constructor(
    private val auth : FirebaseAuth,
    private val bloodPressureReadingsUseCases: BloodPressureReadingUseCases
) {

    private val userid = auth.currentUser?.uid
    private val _getUserBloodPressureReadings = mutableStateOf<Response<BloodPressureReading?>>(Response.Success(null))
    val getUserBloodPressureReadings : State<Response<BloodPressureReading?>> = _getUserBloodPressureReadings

    fun getUserBloodPressureReadings() {
        if(userid != null) {
                bloodPressureReadingsUseCases.getUserBloodPressureReadings(userid = userid).collect {
                    _getUserBloodPressureReadings.value = it
                }
        }
    }
}