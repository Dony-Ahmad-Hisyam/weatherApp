package com.easytuto.realtimeweather.api

sealed class NetworkResponses<out T> {
    data class Success<out T>(val data : T) : NetworkResponses<T>()
    data class Error(val message: String) : NetworkResponses<Nothing>()
    object Loading : NetworkResponses<Nothing>()
}