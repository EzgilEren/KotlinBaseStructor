package com.ezgieren.kotlinbasestructor.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.ezgieren.kotlinbasestructor.data.local.entity.ExampleEntity
import com.ezgieren.kotlinbasestructor.util.Constants

// ExampleEntity ile çalışacak DAO.
// BaseDao’yu genişlettiği için ortak işlemleri devralır.
@Dao
interface ExampleDao : BaseDao<ExampleEntity> {
    @Query(Constants.QueryConstants.GET_ALL_TABLE_NAME)
    suspend fun getAll(): List<ExampleEntity>
}