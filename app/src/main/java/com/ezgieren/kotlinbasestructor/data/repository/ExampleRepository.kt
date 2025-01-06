package com.ezgieren.kotlinbasestructor.data.repository

import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import com.ezgieren.kotlinbasestructor.util.Resource

interface ExampleRepository {
    suspend fun fetchExampleData(param: String): Resource<List<ExampleData>>
}