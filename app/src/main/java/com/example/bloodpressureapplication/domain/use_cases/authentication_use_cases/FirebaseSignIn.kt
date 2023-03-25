package com.example.bloodpressureapplication.domain.use_cases.authentication_use_cases

import com.example.bloodpressureapplication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignIn @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) = repository.firebaseSignIn(email, password)
}