package com.example.mvvmarch.domain.usecase

import com.example.mvvmarch.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetFCMTokenUseCase @Inject constructor(private val repository: FirebaseRepository) {
    suspend operator fun invoke(): String? {
        return repository.getFCMToken()
    }
}