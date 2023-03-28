package com.example.bloodpressureapplication.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.domain.use_cases.heart_rate_reading_use_cases.HeartRateReadingsUseCases
import com.example.bloodpressureapplication.util.Response
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeartRateReadingsViewModel @Inject constructor(
    private val heartRateReadingsUseCases: HeartRateReadingsUseCases,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val userid = auth.currentUser?.uid
    private val _heartRateReadingData = mutableStateOf<Response<List<HeartRateReadings>>> (Response.Loading)
    val heartRateReadingData : State<Response<List<HeartRateReadings>>> = _heartRateReadingData

    private val _uploadHeartRateReadingData = mutableStateOf<Response<Boolean>> (Response.Success(false))
    val uploadHeartRateReadingData : State<Response<Boolean>> = _uploadHeartRateReadingData

    fun getAllHeartReadings() {
        if(userid != null) {
            viewModelScope.launch {
                heartRateReadingsUseCases.getAllHeartReadings(userid = userid).collect {
                    _heartRateReadingData.value = it
                }
            }
        }
    }

    fun uploadHeartReading(bpm: Int, readingStatus: String, timestamp: Timestamp) {
        if (userid != null) {
            viewModelScope.launch {
                heartRateReadingsUseCases.uploadHeartReading(
                    userid = userid,
                    bpm = bpm,
                    readingStatus = readingStatus,
                    timestamp = timestamp
                ).collect {
                    _uploadHeartRateReadingData.value = it
                }
            }
        }
    }

}