package com.example.cmp_b.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.data.model.Post
import com.example.cmp_b.data.repository.PostRepository
import com.example.cmp_b.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    private val _postsState = MutableStateFlow<NetworkResult<List<Post>>>(NetworkResult.Loading)
    val postsState: StateFlow<NetworkResult<List<Post>>> = _postsState

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            repository.getPosts().collect { result ->
                _postsState.value = result
            }
        }
    }
}
