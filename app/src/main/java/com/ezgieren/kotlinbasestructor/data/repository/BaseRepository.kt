package com.ezgieren.kotlinbasestructor.data.repository

import com.ezgieren.kotlinbasestructor.data.remote.api.ExampleApiService
import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val apiService: ExampleApiService
) {
    suspend fun fetchExampleData(param: String): Resource<List<ExampleData>> {
        return try {
            val response = apiService.fetchExampleData(param)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true) {
                    Resource.Success(body.data ?: emptyList())
                } else {
                    Resource.Error(
                        message = Constants.API_ERROR_MESSAGE,
                        detailedMessage = body?.message ?: Constants.NO_DETAILS
                    )
                }
            } else {
                Resource.Error(
                    message = Constants.UNKNOWN_ERROR_MESSAGE,
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