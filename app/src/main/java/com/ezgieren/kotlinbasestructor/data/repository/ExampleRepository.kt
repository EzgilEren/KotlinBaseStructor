package com.ezgieren.kotlinbasestructor.data.repository

import com.ezgieren.kotlinbasestructor.data.remote.api.ExampleApiService
import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import javax.inject.Inject

// Repository for ExampleData
class ExampleRepository @Inject constructor(
    private val apiService: ExampleApiService
) {
    suspend fun fetchExampleData(param: String): Resource<List<ExampleData>> {
        return try {
            val response = apiService.fetchExampleData(param)
            if (response.isSuccessful) {
                Resource.Success(response.body()?.data ?: emptyList())
            } else {
                Resource.Error(
                    message = Constants.API_ERROR_MESSAGE,
                    detailedMessage = response.body()?.message ?: Constants.NO_DETAILS
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