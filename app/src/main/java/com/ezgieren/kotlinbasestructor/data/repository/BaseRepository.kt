package com.ezgieren.kotlinbasestructor.data.repository

import com.ezgieren.kotlinbasestructor.data.remote.model.BaseResponse
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import retrofit2.Response

abstract class BaseRepository {

    /**
     * Güvenli bir şekilde API çağrısı yapar ve sonucu [Resource] olarak döner.
     * @param apiCall Suspend fonksiyonu olarak API çağrısını temsil eder.
     */
    protected suspend fun <T> safeApiCall(apiCall: suspend () -> Response<BaseResponse<T>>): Resource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true && body.data != null) {
                    Resource.Success(body.data)
                } else {
                    Resource.Error(
                        message = body?.message ?: Constants.API_ERROR_MESSAGE,
                        detailedMessage = Constants.NO_DETAILS
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

    /**
     * Room'dan veri çekme işlemleri için genel bir şablon.
     */
    protected suspend fun <T> safeLocalCall(localCall: suspend () -> T?): Resource<T> {
        return try {
            val data = localCall()
            if (data != null) {
                Resource.Success(data)
            } else {
                Resource.Error(
                    message = Constants.LOCAL_ERROR_MESSAGE,
                    detailedMessage = Constants.NO_DETAILS
                )
            }
        } catch (e: Exception) {
            Resource.Error(
                message = Constants.LOCAL_ERROR_MESSAGE,
                detailedMessage = e.localizedMessage ?: Constants.NO_DETAILS
            )
        }
    }
}