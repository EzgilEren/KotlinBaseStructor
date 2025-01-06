package com.ezgieren.kotlinbasestructor.data.remote.api

import com.ezgieren.kotlinbasestructor.data.remote.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostApiService {
    @GET("posts")
    suspend fun fetchPosts(): Response<List<Post>>
}