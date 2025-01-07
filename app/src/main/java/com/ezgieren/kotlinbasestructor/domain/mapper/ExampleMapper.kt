package com.ezgieren.kotlinbasestructor.domain.mapper

import com.ezgieren.kotlinbasestructor.data.local.entity.ExampleEntity
import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import com.ezgieren.kotlinbasestructor.domain.model.ExampleModel
import com.ezgieren.kotlinbasestructor.util.Constants

object ExampleMapper {

    // API'den gelen veriyi Room Entity'e dönüştürür
    fun fromApiToEntity(apiData: ExampleData): ExampleEntity {
        return ExampleEntity(
            id = apiData.id,
            name = apiData.name,
            description = apiData.description ?: Constants.NO_DETAILS
        )
    }

    // Room Entity'den UI'ya uygun modele dönüştürür
    fun fromEntityToModel(entity: ExampleEntity): ExampleModel {
        return ExampleModel(
            id = entity.id,
            name = entity.name,
            email = "example@email.com", // Varsayılan bir veri
            description = entity.description ?: Constants.NO_DETAILS
        )
    }

    // Domain Model -> UI Model (Eğer gerekirse)
    fun fromDomainToUI(domainModel: ExampleModel): ExampleData {
        return ExampleData(
            id = domainModel.id,
            name = domainModel.name,
            description = domainModel.description
        )
    }
}