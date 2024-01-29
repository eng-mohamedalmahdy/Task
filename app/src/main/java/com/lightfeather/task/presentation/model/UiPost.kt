package com.lightfeather.task.presentation.model

import com.lightfeather.task.domain.model.DomainPost

data class UiPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

fun DomainPost.toUiPost() = UiPost(
    userId, id, title, body
)
