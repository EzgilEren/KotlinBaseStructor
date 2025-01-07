package com.ezgieren.kotlinbasestructor.data.repository

import com.ezgieren.kotlinbasestructor.data.local.dao.ExampleDao
import com.ezgieren.kotlinbasestructor.data.local.entity.ExampleEntity
import com.ezgieren.kotlinbasestructor.data.remote.api.ExampleApiService
import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import com.ezgieren.kotlinbasestructor.domain.mapper.ExampleMapper
import com.ezgieren.kotlinbasestructor.domain.repository.ExampleRepository
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val apiService: ExampleApiService,
    private val exampleDao: ExampleDao
) : BaseRepository(), ExampleRepository {

    override suspend fun fetchExamples(param: String): Resource<List<ExampleEntity>> {
        // 1. Room'dan veriyi çek
        val localData = exampleDao.getAll()
        if (localData.isNotEmpty()) {
            return Resource.Success(localData)
        }

        // 2. API'den veriyi çek
        val apiResponse: Resource<List<ExampleData>> = safeApiCall { apiService.fetchExampleData(param) }
        return when (apiResponse) {
            is Resource.Success -> {
                val remoteData = apiResponse.data?.map { ExampleMapper.fromApiToEntity(it) } ?: emptyList()

                // 3. Veriyi Room'a kaydet
                exampleDao.insert(remoteData)

                Resource.Success(remoteData)
            }
            is Resource.Error -> {
                Resource.Error(
                    message = apiResponse.message ?: Constants.API_ERROR_MESSAGE,
                    detailedMessage = apiResponse.detailedMessage
                )
            }
            else -> Resource.Error(Constants.UNKNOWN_ERROR_MESSAGE)
        }
    }

    // Room'dan veriyi al
    override suspend fun getLocalExamples(): Resource<List<ExampleEntity>> {
        return try {
            val entities = exampleDao.getAll()
            Resource.Success(entities)
        } catch (e: Exception) {
            Resource.Error("${Constants.LOCAL_ERROR_MESSAGE}: ${e.localizedMessage}")
        }
    }
}