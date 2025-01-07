package com.ezgieren.kotlinbasestructor.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezgieren.kotlinbasestructor.data.local.dao.ExampleDao
import com.ezgieren.kotlinbasestructor.data.local.entity.ExampleEntity

//  Room’un ana veritabanı sınıfıdır.
// Tüm Entity’leri ve DAO’ları bir araya getiren Room Database sınıfı.
//  Tüm DAO’ları bir araya getirir ve Room veritabanını başlatır.
@Database(entities = [ExampleEntity::class], version = 1, exportSchema = false)
abstract class BaseDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}