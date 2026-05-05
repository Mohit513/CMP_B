package com.example.cmp_b.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_b.core.utils.NetworkResult
import com.example.cmp_b.feature.home.domain.usecase.GetHomePostsUseCase
import com.example.cmp_b.feature.home.presentation.intent.HomeScreenIntent
import com.example.cmp_b.feature.home.presentation.state.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomePostsUseCase: GetHomePostsUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()
    
    init {
        handleIntent(HomeScreenIntent.LoadPosts)
    }
    
    fun handleIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.LoadPosts -> loadPosts()
            is HomeScreenIntent.RefreshPosts -> refreshPosts()
            is HomeScreenIntent.ToggleFavorite -> toggleFavorite(intent.postId)
        }
    }
    
    private fun loadPosts() {
        viewModelScope.launch {
            getHomePostsUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _state.value = _state.value.copy(isLoading = true, error = null)
                    }
                    is NetworkResult.Success -> {
                        _state.value = _state.value.copy(
                            posts = result.data,
                            isLoading = false,
                            error = null,
                            isRefreshing = false
                        )
                    }
                    is NetworkResult.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message,
                            isRefreshing = false
                        )
                    }
                }
            }
        }
    }
    
    private fun refreshPosts() {
        _state.value = _state.value.copy(isRefreshing = true, error = null)
        loadPosts()
    }
    
    private fun toggleFavorite(postId: Int) {
        val updatedPosts = _state.value.posts.map { post ->
            if (post.id == postId) {
                post.copy(isFavorite = !post.isFavorite)
            } else {
                post
            }
        }
        _state.value = _state.value.copy(posts = updatedPosts)
    }
}
