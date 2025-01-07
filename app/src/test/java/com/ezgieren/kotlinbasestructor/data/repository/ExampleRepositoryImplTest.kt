package com.ezgieren.kotlinbasestructor.data.repository

import com.ezgieren.kotlinbasestructor.data.local.dao.ExampleDao
import com.ezgieren.kotlinbasestructor.data.local.entity.ExampleEntity
import com.ezgieren.kotlinbasestructor.data.remote.api.ExampleApiService
import com.ezgieren.kotlinbasestructor.data.remote.model.BaseResponse
import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import com.ezgieren.kotlinbasestructor.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ExampleRepositoryImplTest {

    private lateinit var repository: ExampleRepositoryImpl
    private val mockApiService = mockk<ExampleApiService>()
    private val mockExampleDao = mockk<ExampleDao>(relaxed = true)

    @Before
    fun setup() {
        repository = ExampleRepositoryImpl(mockApiService, mockExampleDao)
    }

    @Test
    fun `fetchExamples should return data from Room if available`() = runBlocking {
        // Arrange
        val localData = listOf(ExampleEntity(1, "Test Name", "Test Description"))
        coEvery { mockExampleDao.getAll() } returns localData

        // Act
        val result = repository.fetchExamples("testParam")

        // Assert
        assert(result is Resource.Success)
        assertEquals(localData, (result as Resource.Success).data)
        coVerify { mockExampleDao.getAll() } // Verify that Room method was called
    }

    @Test
    fun `fetchExamples should fetch data from API if Room is empty`() = runBlocking {
        // Arrange
        val apiData = listOf(ExampleData(1, "API Name", "API Description"))
        val apiResponse = BaseResponse(true, apiData, null)
        coEvery { mockExampleDao.getAll() } returns emptyList()
        coEvery { mockApiService.fetchExampleData("testParam") } returns retrofit2.Response.success(apiResponse)

        // Act
        val result = repository.fetchExamples("testParam")

        // Assert
        assert(result is Resource.Success)
        assertEquals(apiData.size, (result as Resource.Success).data?.size)
        coVerify { mockApiService.fetchExampleData("testParam") } // Verify API call
    }

    @Test
    fun `fetchExamples should return error when API fails`() = runBlocking {
        // Arrange
        coEvery { mockExampleDao.getAll() } returns emptyList()
        coEvery { mockApiService.fetchExampleData("testParam") } throws Exception("Network Error")

        // Act
        val result = repository.fetchExamples("testParam")

        // Assert
        assert(result is Resource.Error)
        assertEquals("Network Error", (result as Resource.Error).detailedMessage)
    }
}