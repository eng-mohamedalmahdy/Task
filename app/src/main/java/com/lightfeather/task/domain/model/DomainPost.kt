package com.lightfeather.task.domain.model

data class DomainPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)