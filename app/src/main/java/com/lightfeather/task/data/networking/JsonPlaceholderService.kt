package com.lightfeather.task.data.networking

import com.lightfeather.task.data.networking.model.APIPost
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderService {

    @GET("/posts")
    suspend fun getPosts(): List<APIPost>

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): APIPost

}