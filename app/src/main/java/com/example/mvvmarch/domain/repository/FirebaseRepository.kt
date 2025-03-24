package com.example.mvvmarch.domain.repository

interface FirebaseRepository {

    suspend fun getFCMToken(): String?

    suspend fun subscribeToTopic(topic: String)
}