package com.example.cmp_b.shared.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.core.utils.NetworkResult
import com.example.cmp_b.shared.domain.usecase.GetPostsUseCase
import com.example.cmp_b.shared.presentation.intent.PostListIntent
import com.example.cmp_b.shared.presentation.state.PostListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostListViewModel(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(PostListState())
    val state: StateFlow<PostListState> = _state.asStateFlow()
    
    init {
        handleIntent(PostListIntent.LoadPosts)
    }
    
    fun handleIntent(intent: PostListIntent) {
        when (intent) {
            is PostListIntent.LoadPosts -> loadPosts()
            is PostListIntent.RefreshPosts -> refreshPosts()
        }
    }
    
    private fun loadPosts() {
        viewModelScope.launch {
            getPostsUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _state.value = _state.value.copy(isLoading = true, error = null)
                    }
                    is NetworkResult.Success -> {
                        _state.value = _state.value.copy(
                            posts = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is NetworkResult.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    
    private fun refreshPosts() {
        _state.value = _state.value.copy(error = null)
        loadPosts()
    }
}
