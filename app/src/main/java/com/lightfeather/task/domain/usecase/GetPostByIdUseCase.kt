package com.lightfeather.task.domain.usecase

import com.lightfeather.task.domain.repository.PostsRepository

class GetPostByIdUseCase(private val repository: PostsRepository) {

    suspend operator fun invoke(id: Int) = repository.getPostById(id);

}
