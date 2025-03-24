package com.example.mvvmarch.data.repository

import android.util.Log
import com.example.mvvmarch.domain.repository.FirebaseRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl: FirebaseRepository {
    override suspend fun getFCMToken(): String? {
        return try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            Log.e("FCM", "Error fetching FCM token", e)
            e.printStackTrace()
            null
        }
    }

    override suspend fun subscribeToTopic(topic: String) {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic(topic).await()
            Log.d("FCM", "Subscribed to topic: $topic")
        } catch (e: Exception) {
            Log.e("FCM", "Error subscribing to topic", e)
            e.printStackTrace()
        }
    }
}