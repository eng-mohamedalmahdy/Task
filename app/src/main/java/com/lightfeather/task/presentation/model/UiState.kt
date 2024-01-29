package com.lightfeather.task.presentation.model

sealed class UiState<out T> {
    data object Idle : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val t: Throwable) : UiState<Nothing>()
}