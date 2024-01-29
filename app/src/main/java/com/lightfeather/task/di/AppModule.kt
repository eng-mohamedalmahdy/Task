package com.lightfeather.task.di

import com.lightfeather.task.data.networking.JsonPlaceholderService
import com.lightfeather.task.data.networking.PostsRepositoryRemoteImp
import com.lightfeather.task.data.networking.RetrofitClient
import com.lightfeather.task.domain.repository.PostsRepository
import com.lightfeather.task.domain.usecase.GetAllPostsUseCase
import com.lightfeather.task.domain.usecase.GetPostByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideJsonPlaceholderService(): JsonPlaceholderService {
        return RetrofitClient.create()
    }

    @Provides
    fun providePostRepository(service: JsonPlaceholderService): PostsRepository {
        return PostsRepositoryRemoteImp(service)
    }

    @Provides
    fun providePostUseCase(repository: PostsRepository): GetAllPostsUseCase {
        return GetAllPostsUseCase(repository)
    }

    @Provides
    fun providesGetPostById(repository: PostsRepository): GetPostByIdUseCase {
        return GetPostByIdUseCase(repository)
    }

}
