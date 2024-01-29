package com.lightfeather.task.domain.usecase

import com.lightfeather.task.domain.repository.PostsRepository

class GetAllPostsUseCase(private val repository: PostsRepository) {

    suspend operator fun invoke() = repository.getAllPosts();

}
