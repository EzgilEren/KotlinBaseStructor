package com.ezgieren.kotlinbasestructor.data.remote.model

data class BaseResponse<T>(
    val success: Boolean,
    val data: T?,
    val message: String?
)

data class ExampleData(
    val id: Int,
    val name: String,
    val description: String?
)