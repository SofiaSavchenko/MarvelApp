package com.example.full.presentation

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
class FullCardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FullCardUiState>(FullCardUiState.Loading)
    val uiState: StateFlow<FullCardUiState> = _uiState.asStateFlow()

    fun observeDataById(id: Int) {

        viewModelScope.launch {

            repository.getCharacterById(id)
                .catch { error ->
                    _uiState.emit(FullCardUiState.Error(error))
                }
                .collect { character ->
                    _uiState.emit(FullCardUiState.Success(character))
                }
        }
    }
}
