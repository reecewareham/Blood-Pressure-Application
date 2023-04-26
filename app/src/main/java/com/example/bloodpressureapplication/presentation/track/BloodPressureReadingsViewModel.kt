package com.example.bloodpressureapplication.presentation.track

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

////////////////////////////////////////////////////////////////////
/*
Blood Pressure Reading View Model. Used as the middle ground between the
backend Firestore database system and the user interface.
*/
////////////////////////////////////////////////////////////////////

@HiltViewModel
class BloodPressureReadingsViewModel @Inject constructor(
    private val bloodPressureReadingsUseCases: BloodPressureReadingsUseCases,
    private val auth: FirebaseAuth
) : ViewModel() {

    ////////////////////////////////////////////////////////////////////
    /*
    States. Used to notify the user interface that a backend function has
    completed. If a function is successful, it will set the corresponding
    state to true which will notify the user interface to make a change.
    */
    ////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////
    /*
    getAllReadings. Interacts with the getAllReadings use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets bloodPressureReadingsData to the result which is a
    list of readings. The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun getAllReadings() {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getAllReadings(userid = userid).collect {
                    _bloodPressureReadingsData.value = it
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    getLastReading. Interacts with the getLastReading use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets bloodPressureLastReadingData to the result which is a
    list of the last reading. The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun getLastReading() {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getLastReading(userid = userid).collect {
                    _bloodPressureLastReadingData.value = it
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    getLast5Readings. Interacts with the getLast5Readings use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets bloodPressure5ReadingData to the result which is a
    list of the last readings. The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun getLast5Readings() {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getLast5Readings(userid = userid).collect {
                    _bloodPressure5ReadingData.value = it
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    getReading. Interacts with the getReading use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets bloodPressureReadingData to the result which is a
    single reading. The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

    fun getReading(bloodPressureReadingId: String) {
        if(userid != null) {
            viewModelScope.launch {
                bloodPressureReadingsUseCases.getReading(bloodPressureReadingId = bloodPressureReadingId).collect {
                    _bloodPressureReadingData.value = it
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////
    /*
    uploadReading. Interacts with the uploadReading use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets uploadBloodPressureReadingData to the result which is
    either true or false. The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////
    /*
    updateReading. Interacts with the updateReading use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets updateBloodPressureReadingData to the result which is
    either true or false. The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

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

    ////////////////////////////////////////////////////////////////////
    /*
    deleteReading. Interacts with the deleteReading use case which runs the
    corresponding implementation function. Collects the result of the
    function and sets deleteBloodPressureReadingData to the result which is
    either true or false. The result is used in the user interface.
    */
    ////////////////////////////////////////////////////////////////////

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