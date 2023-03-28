package com.example.bloodpressureapplication.di

import com.example.bloodpressureapplication.data.AuthenticationRepositoryImpl
import com.example.bloodpressureapplication.data.BloodPressureReadingsRepositoryImpl
import com.example.bloodpressureapplication.data.UserRepositoryImpl
import com.example.bloodpressureapplication.domain.repository.AuthenticationRepository
import com.example.bloodpressureapplication.domain.repository.BloodPressureReadingsRepository
import com.example.bloodpressureapplication.domain.repository.UserRepository
import com.example.bloodpressureapplication.domain.use_cases.*
import com.example.bloodpressureapplication.domain.use_cases.authentication_use_cases.*
import com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases.BloodPressureReadingsUseCases
import com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases.GetAllReadings
import com.example.bloodpressureapplication.domain.use_cases.blood_pressure_reading_use_cases.UploadReading
import com.example.bloodpressureapplication.domain.use_cases.user_use_cases.GetUserDetails
import com.example.bloodpressureapplication.domain.use_cases.user_use_cases.SetUserDetails
import com.example.bloodpressureapplication.domain.use_cases.user_use_cases.UserUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BloodPressureAppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage() : FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore) : AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth = auth, firestore = firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository = repository),
        firebaseSignIn = FirebaseSignIn(repository = repository),
        firebaseSignUp = FirebaseSignUp(repository = repository)
    )

    @Singleton
    @Provides
    fun provideUserRepository(firebaseFirestore: FirebaseFirestore) : UserRepository {
        return UserRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(repository: UserRepository) = UserUseCases(
        getUserDetails = GetUserDetails(repository = repository),
        setUserDetails = SetUserDetails(repository = repository)
    )

    @Singleton
    @Provides
    fun provideBloodPressureReadingsRepository(firebaseFirestore: FirebaseFirestore) : BloodPressureReadingsRepository {
        return BloodPressureReadingsRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }

    @Singleton
    @Provides
    fun provideBloodPressureReadingsUseCases(repository: BloodPressureReadingsRepository) = BloodPressureReadingsUseCases(
        getAllReadings = GetAllReadings(repository = repository),
        uploadReading = UploadReading(repository = repository)
    )
}