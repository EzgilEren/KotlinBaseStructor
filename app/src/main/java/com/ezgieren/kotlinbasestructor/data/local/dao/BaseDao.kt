package com.ezgieren.kotlinbasestructor.data.local.dao

import androidx.room.*
import com.ezgieren.kotlinbasestructor.data.local.entity.BaseEntity
import com.ezgieren.kotlinbasestructor.util.Constants

// Tüm DAO sınıfları için ortak işlemleri (insert, update, delete vb.) tanımlar.
// Örneğin, tüm tablolarda veri ekleme ve silme işlemleri geneldir.
interface BaseDao<T : BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<T>)

    @Update
    suspend fun update(item: T)

    @Delete
    suspend fun delete(item: T)

    @Query("DELETE FROM  ${Constants.Database.EXAMPLE_TABLE}")
    suspend fun deleteAll()

    @Query("SELECT * FROM example_table WHERE id = :id")
    suspend fun getById(id: Int): T?
}
