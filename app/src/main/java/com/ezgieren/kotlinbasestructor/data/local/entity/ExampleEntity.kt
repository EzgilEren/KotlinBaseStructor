package com.ezgieren.kotlinbasestructor.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ezgieren.kotlinbasestructor.util.Constants

// Room tarafından bir tablo olarak temsil edilen bir veri modelidir.
// Örneğin, bir ExampleEntity tablosu id, name ve description alanlarını içerir.
@Entity(tableName = Constants.Database.EXAMPLE_TABLE)
data class ExampleEntity(
    @PrimaryKey override val id: Int,
    val name: String,
    val description: String
) : BaseEntity