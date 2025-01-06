package com.ezgieren.kotlinbasestructor.data.remote.model

data class ApiExampleData(
    var i : Unit
)

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)