package com.ezgieren.kotlinbasestructor.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.kotlinbasestructor.data.local.entity.ExampleEntity
import com.ezgieren.kotlinbasestructor.data.repository.ExampleRepositoryImpl
import com.ezgieren.kotlinbasestructor.util.Constants
import com.ezgieren.kotlinbasestructor.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ExampleRepositoryImpl
) : ViewModel() {

    private val _dataState = MutableStateFlow<Resource<List<ExampleEntity>>>(Resource.Loading())
    val dataState: StateFlow<Resource<List<ExampleEntity>>> get() = _dataState

    /**
     * Hem API'den hem de Room'dan veri çekme işlemi.
     * Room'dan veri çekilemezse API'den çekmeyi dener.
     */
    fun fetchExamples(param: String) {
        viewModelScope.launch {
            _dataState.value = Resource.Loading()

            // 1. Room'dan veri çek
            val localData = repository.getLocalExamples()
            if (localData is Resource.Success && localData.data?.isNotEmpty() == true) {
                _dataState.value = Resource.Success(localData.data)
                return@launch
            }

            // 2. API'den veri çek
            val apiResult = repository.fetchExamples(param)
            when (apiResult) {
                is Resource.Success -> {
                    val remoteData = apiResult.data ?: emptyList()
                    _dataState.value = Resource.Success(remoteData)
                }

                is Resource.Error -> {
                    _dataState.value = Resource.Error(
                        message = apiResult.message ?: Constants.API_ERROR_MESSAGE,
                        detailedMessage = apiResult.detailedMessage
                    )
                }

                else -> {
                    _dataState.value = Resource.Error(Constants.UNKNOWN_ERROR_MESSAGE)
                }
            }
        }
    }
}