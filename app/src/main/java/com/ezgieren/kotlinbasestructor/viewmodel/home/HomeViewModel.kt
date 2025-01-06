package com.ezgieren.kotlinbasestructor.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.kotlinbasestructor.data.remote.model.ExampleData
import com.ezgieren.kotlinbasestructor.data.repository.BaseRepository
import com.ezgieren.kotlinbasestructor.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BaseRepository
) : ViewModel() {

    private val _dataState = MutableStateFlow<Resource<List<ExampleData>>>(Resource.Loading())
    val dataState: StateFlow<Resource<List<ExampleData>>> get() = _dataState

    fun fetchData(param: String) {
        viewModelScope.launch {
            _dataState.value = Resource.Loading() // Loading status
            _dataState.value = repository.fetchExampleData(param)
        }
    }
}