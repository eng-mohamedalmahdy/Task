package com.lightfeather.task.domain.model

sealed class DomainResult<T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error<T>(val error: Throwable) : DomainResult<T>()
}