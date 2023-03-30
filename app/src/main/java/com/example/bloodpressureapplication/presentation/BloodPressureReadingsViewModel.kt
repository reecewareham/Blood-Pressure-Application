package com.example.bloodpressureapplication.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases.BloodPressureReadingsUseCases
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BloodPressureReadingsViewModel @Inject constructor(
    private val bloodPressureReadingsUseCases: BloodPressureReadingsUseCases,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val userid = auth.currentUser?.uid
    private val _bloodPressureReadingData = mutableStateOf<Response<List<BloodPressureReadings>>> (Response.Loading)
    val bloodPressureReadingData : State<Response<List<BloodPressureReadings>>> = _bloodPressureReadingData

    private val _bloodPressure5ReadingData = mutableStateOf<Response<List<BloodPressureReadings>>> (Response.Loading)
    val bloodPressure5ReadingData : State<Response<List<BloodPressureReadings>>> = _bloodPressure5ReadingData

    private val _uploadBloodPressureReadingData = mutableStateOf<Response<Boolean>> (Response.Success(false))
    val uploadBloodPressureReadingData : State<Response<Boolean>> = _uploadBloodPressureReadingData

    fun getAllReadings() {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getAllReadings(userid = userid).collect {
                    _bloodPressureReadingData.value = it
                }
            }
        }
    }

    fun getLast5Readings() {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getLast5Readings(userid = userid).collect {
                    _bloodPressure5ReadingData.value = it
                }
            }
        }
    }

    fun uploadReading(systolicPressure: Int, diastolicPressure: Int, timestamp: Timestamp) {
        if (userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.uploadReading(
                    userid = userid,
                    systolicPressure = systolicPressure,
                    diastolicPressure = diastolicPressure,
                    timestamp = timestamp
                ).collect {
                    _uploadBloodPressureReadingData.value = it
                }
            }
        }
    }

}