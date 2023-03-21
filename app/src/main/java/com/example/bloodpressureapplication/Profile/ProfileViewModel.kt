package com.example.bloodpressureapplication.Profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloodpressureapplication.data.MongoRepository
import com.example.bloodpressureapplication.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MongoRepository
) : ViewModel() {
    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var DOB = mutableStateOf("")
    var data = mutableStateOf(emptyList<User>())

    init {
        viewModelScope.launch {
            repository.getData().collect {
                data.value = it
            }
        }
    }
}