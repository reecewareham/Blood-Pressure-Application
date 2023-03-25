package com.example.bloodpressureapplication.domain.use_cases.authentication_use_cases

import com.example.bloodpressureapplication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String, firstName: String, lastName: String, age: String, imageUrl: String) = repository.firebaseSignUp(email, password, firstName, lastName, age, imageUrl)
}