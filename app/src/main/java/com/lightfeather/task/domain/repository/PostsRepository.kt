package com.lightfeather.task.domain.repository

import com.lightfeather.task.domain.model.DomainPost
import com.lightfeather.task.domain.model.DomainResult

interface PostsRepository {
    suspend fun getAllPosts(): DomainResult<List<DomainPost>>
    suspend fun getPostById(id: Int): DomainResult<DomainPost>
}