package com.ezgieren.kotlinbasestructor.viewmodel.exampleApi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import com.ezgieren.kotlinbasestructor.data.repository.ExampleRepository
import com.ezgieren.kotlinbasestructor.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for ExampleData API
@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel() {

    private val _dataState = MutableStateFlow<Resource<List<ExampleData>>>(Resource.Loading())
    val dataState: StateFlow<Resource<List<ExampleData>>> get() = _dataState

    fun fetchExampleData(param: String) {
        viewModelScope.launch {
            _dataState.value = Resource.Loading()
            _dataState.value = repository.fetchExampleData(param)
        }
    }
}
