package com.ezgieren.kotlinbasestructor.viewmodel.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezgieren.kotlinbasestructor.data.remote.model.Post
import com.ezgieren.kotlinbasestructor.data.repository.PostRepository
import com.ezgieren.kotlinbasestructor.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for Post API
@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _dataState = MutableStateFlow<Resource<List<Post>>>(Resource.Loading())
    val dataState: StateFlow<Resource<List<Post>>> get() = _dataState

    fun fetchPosts() {
        viewModelScope.launch {
            _dataState.value = repository.fetchPosts()
        }
    }
}