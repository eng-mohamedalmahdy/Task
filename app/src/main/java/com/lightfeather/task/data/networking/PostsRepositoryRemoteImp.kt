package com.lightfeather.task.data.networking

import com.lightfeather.task.data.networking.model.toPost
import com.lightfeather.task.domain.repository.PostsRepository
import com.lightfeather.task.domain.model.DomainPost
import com.lightfeather.task.domain.model.DomainResult

class PostsRepositoryRemoteImp(private val service: JsonPlaceholderService) : PostsRepository {

    override suspend fun getAllPosts(): DomainResult<List<DomainPost>> =
        runCatching {
            DomainResult.Success(service.getPosts().map { it.toPost() })
        }.getOrElse {
            DomainResult.Error(it)
        }

    override suspend fun getPostById(id: Int): DomainResult<DomainPost> =
        runCatching {
            DomainResult.Success(service.getPostById(id).toPost())
        }.getOrElse {
            DomainResult.Error(it)
        }

}