package com.ezgieren.kotlinbasestructor.data.repository

import com.ezgieren.kotlinbasestructor.data.remote.api.PostApiService
import com.ezgieren.kotlinbasestructor.data.remote.model.Post
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import javax.inject.Inject

// Repository for Post
class PostRepository @Inject constructor(
    private val apiService: PostApiService
) {
    suspend fun fetchPosts(): Resource<List<Post>> {
        return try {
            val response = apiService.fetchPosts()
            if (response.isSuccessful) {
                Resource.Success(response.body() ?: emptyList())
            } else {
                Resource.Error(
                    message = Constants.API_ERROR_MESSAGE,
                    detailedMessage = response.message()
                )
            }
        } catch (e: Exception) {
            Resource.Error(
                message = Constants.NETWORK_ERROR_MESSAGE,
                detailedMessage = e.localizedMessage ?: Constants.NO_DETAILS
            )
        }
    }
}