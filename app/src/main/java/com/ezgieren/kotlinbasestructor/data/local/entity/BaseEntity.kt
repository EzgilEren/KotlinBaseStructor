package com.ezgieren.kotlinbasestructor.data.local.entity

// Tüm Room tabanlı veritabanı varlıklarının (entities) ortak özelliklerini içerir.
// Örneğin, her tablo bir id barındırır.
interface BaseEntity {
    val id: Int
}