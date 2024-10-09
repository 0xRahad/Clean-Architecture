package com.rahad.jetshop.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahad.jetshop.common.Resource
import com.rahad.jetshop.domain.use_cases.GetSearchImageUseCase
import com.rahad.jetshop.ui.ImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ImageViewModel @Inject constructor(
    private val useCase: GetSearchImageUseCase
) : ViewModel() {

    private val _imageList = mutableStateOf(ImageState())
    val imageState : State<ImageState> get() = _imageList

    private val _query = MutableStateFlow("")

    init {
        getSearchImage("")
        viewModelScope.launch {
            _query.debounce(500).collectLatest {
                getSearchImage(query = it)
            }
        }
    }
    fun updateQuery(str:String){
        _query.value = str
    }
    fun getSearchImage(query:String){
        useCase.invoke(query = query).onEach {
            when(it){
                is Resource.Error -> {
                    _imageList.value = ImageState(error = it.message.toString())
                }
                is Resource.Loading -> {
                    _imageList.value = ImageState(isLoading = true)
                }
                is Resource.Success -> {
                    _imageList.value = ImageState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}