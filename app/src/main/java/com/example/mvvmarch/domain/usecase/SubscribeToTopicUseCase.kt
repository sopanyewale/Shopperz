package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.domain.repository.FirebaseRepository
import javax.inject.Inject

class SubscribeToTopicUseCase @Inject constructor(private val repository: FirebaseRepository) {
    suspend operator fun invoke(topic: String) {
        repository.subscribeToTopic(topic)
    }
}