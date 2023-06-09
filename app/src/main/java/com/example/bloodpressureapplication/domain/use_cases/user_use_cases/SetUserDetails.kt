package com.example.bloodpressureapplication.domain.use_cases.user_use_cases

import com.example.bloodpressureapplication.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(
    private val repository: UserRepository
){
    operator fun invoke(userid: String, firstName: String, lastName: String, age: String, imageUrl: String, email: String, password: String, oldEmail: String, oldPassword: String) =
        repository.setUserDetails(userid = userid, firstName = firstName, lastName = lastName, age = age, imageUrl = imageUrl, email = email, password = password, oldEmail = oldEmail, oldPassword = oldPassword)
}