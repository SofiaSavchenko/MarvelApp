package com.example.slide.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SlideUiState>(SlideUiState.Loading)
    val uiState: StateFlow<SlideUiState> = _uiState.asStateFlow()

    fun observeData() {

        viewModelScope.launch {

            repository.getCharacters()
                .catch { error ->
                    _uiState.emit(SlideUiState.Error(error))
                }
                .collect { characterList ->
                    _uiState.emit(SlideUiState.Success(characterList))
                }
        }
    }
}
