package com.example.mvvmarch.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmarch.domain.usecase.GetFCMTokenUseCase
import com.example.mvvmarch.domain.usecase.SubscribeToTopicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getFCMTokenUseCase: GetFCMTokenUseCase,
    private val subscribeToTopicUseCase: SubscribeToTopicUseCase
) : ViewModel() {

    private val _fcmToken = MutableLiveData<String>()
    val fcmToken: LiveData<String> = _fcmToken

    fun getFCMToken() {
        viewModelScope.launch {
            _fcmToken.value = getFCMTokenUseCase() ?: "0"
        }
    }

    fun subscribeToTopic(topic: String) {
        viewModelScope.launch {
            subscribeToTopicUseCase(topic)
        }
    }
}