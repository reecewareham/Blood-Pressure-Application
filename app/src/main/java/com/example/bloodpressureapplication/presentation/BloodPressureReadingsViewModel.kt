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
    private val _bloodPressureReadingsData = mutableStateOf<Response<List<BloodPressureReadings>>> (Response.Loading)
    val bloodPressureReadingsData : State<Response<List<BloodPressureReadings>>> = _bloodPressureReadingsData

    private val _bloodPressureLastReadingData = mutableStateOf<Response<List<BloodPressureReadings>>> (Response.Loading)
    val bloodPressureLastReadingData : State<Response<List<BloodPressureReadings>>> = _bloodPressureLastReadingData

    private val _bloodPressure5ReadingData = mutableStateOf<Response<List<BloodPressureReadings>>> (Response.Loading)
    val bloodPressure5ReadingData : State<Response<List<BloodPressureReadings>>> = _bloodPressure5ReadingData

    private val _bloodPressureReadingData = mutableStateOf<Response<BloodPressureReadings?>> (Response.Success(null))
    val bloodPressureReadingData : State<Response<BloodPressureReadings?>> = _bloodPressureReadingData

    private val _uploadBloodPressureReadingData = mutableStateOf<Response<Boolean>> (Response.Success(false))
    val uploadBloodPressureReadingData : State<Response<Boolean>> = _uploadBloodPressureReadingData

    private val _updateBloodPressureReadingData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val updateBloodPressureReadingData : State<Response<Boolean>> = _updateBloodPressureReadingData

    private val _deleteBloodPressureReadingData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val deleteBloodPressureReadingData : State<Response<Boolean>> = _deleteBloodPressureReadingData

    fun getAllReadings() {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getAllReadings(userid = userid).collect {
                    _bloodPressureReadingsData.value = it
                }
            }
        }
    }

    fun getLastReading() {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getLastReading(userid = userid).collect {
                    _bloodPressureLastReadingData.value = it
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

    fun getReading(bloodPressureReadingId: String) {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getReading(bloodPressureReadingId = bloodPressureReadingId).collect {
                    _bloodPressureReadingData.value = it
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

    fun updateReading(bloodPressureReadingId : String, systolicPressure: Int, diastolicPressure: Int) {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.updateReading(
                    bloodPressureReadingId = bloodPressureReadingId,
                    systolicPressure = systolicPressure,
                    diastolicPressure = diastolicPressure
                ).collect {
                    _updateBloodPressureReadingData.value = it
                }
            }
        }
    }

    fun deleteReading(bloodPressureReadingId : String) {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.deleteReading(
                    bloodPressureReadingId = bloodPressureReadingId
                ).collect {
                    _deleteBloodPressureReadingData.value = it
                }
            }
        }
    }

}