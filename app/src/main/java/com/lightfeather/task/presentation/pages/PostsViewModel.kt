package com.lightfeather.task.presentation.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lightfeather.task.domain.model.DomainResult
import com.lightfeather.task.domain.usecase.GetAllPostsUseCase
import com.lightfeather.task.domain.usecase.GetPostByIdUseCase
import com.lightfeather.task.presentation.model.UiPost
import com.lightfeather.task.presentation.model.UiState
import com.lightfeather.task.presentation.model.toUiPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetAllPostsUseCase,
    private val getPostByIdUseCase: GetPostByIdUseCase
) : ViewModel() {
    private val _postsList = MutableStateFlow<UiState<List<UiPost>>>(UiState.Idle)
    val postsList = _postsList.asStateFlow()

    private val _postDetailsFlow = MutableStateFlow<UiState<UiPost>>(UiState.Idle)
    val postDetailsFlow = _postDetailsFlow.asStateFlow()


    fun loadPosts() {
        viewModelScope.launch {
            _postsList.update { UiState.Loading }
            _postsList.update {
                getPostsUseCase().let {
                    when (it) {
                        is DomainResult.Error -> UiState.Error(it.error)
                        is DomainResult.Success -> UiState.Success(it.data.map { it.toUiPost() })
                    }
                }
            }
        }
    }

    fun loadPostById(id: Int) {
        viewModelScope.launch {
            _postDetailsFlow.update { UiState.Loading }
            _postDetailsFlow.update {
                getPostByIdUseCase(id).let {
                    when (it) {
                        is DomainResult.Error -> UiState.Error(it.error)
                        is DomainResult.Success -> UiState.Success(it.data.toUiPost())
                    }
                }
            }
        }
    }

}