package com.example.bloodpressureapplication.domain.use_cases.user_use_cases

import com.example.bloodpressureapplication.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    private val repository: UserRepository
){
    operator fun invoke(userid: String) = repository.getUserDetails(userid = userid)
}