package com.ezgieren.kotlinbasestructor.domain.repository

import com.ezgieren.kotlinbasestructor.data.local.entity.ExampleEntity
import com.ezgieren.kotlinbasestructor.util.Resource

interface ExampleRepository {
    /**
     * API'den ve Room'dan veri çeker.
     * Eğer Room'da veri varsa direkt döner, yoksa API çağrısı yapar.
     */
    suspend fun fetchExamples(param: String): Resource<List<ExampleEntity>>
    /**
     * Sadece Room'daki verileri döner.
     */
    suspend fun getLocalExamples():Resource<List<ExampleEntity>>
}