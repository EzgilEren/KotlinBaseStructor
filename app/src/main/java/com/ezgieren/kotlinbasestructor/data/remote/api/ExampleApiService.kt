package com.ezgieren.kotlinbasestructor.data.remote.api

import com.ezgieren.kotlinbasestructor.data.remote.model.BaseResponse
import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleApiService {
    @GET("exampleEndpoint")
    suspend fun fetchExampleData(
        @Query("param") param: String
    ): Response<BaseResponse<List<ExampleData>>>
}