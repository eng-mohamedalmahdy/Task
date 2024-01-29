package com.lightfeather.task.data.networking.model

import com.lightfeather.task.domain.model.DomainPost

data class APIPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

fun APIPost.toPost() =
    DomainPost(
        userId = userId,
        id = id,
        title = title,
        body = body
    )