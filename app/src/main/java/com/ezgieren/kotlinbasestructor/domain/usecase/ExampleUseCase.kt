package com.ezgieren.kotlinbasestructor.domain.usecase

import com.ezgieren.kotlinbasestructor.data.local.entity.ExampleEntity
import com.ezgieren.kotlinbasestructor.domain.repository.ExampleRepository
import com.ezgieren.kotlinbasestructor.util.Resource
import javax.inject.Inject

class ExampleUseCase @Inject constructor(
    private val repository: ExampleRepository
) {
    suspend fun fetchData(param: String): Resource<List<ExampleEntity>> {
        return repository.fetchExamples(param)
    }
}