package com.example.marvelapp.presentation.screens.slide_screen.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.remote.model.either.Either
import com.example.marvelapp.domain.repo.Repository
import com.example.marvelapp.presentation.screens.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SlideUiState())
    val uiState: StateFlow<SlideUiState> = _uiState.asStateFlow()

    suspend fun observeData() {

        viewModelScope.launch {

            when (val result = repository.getCharacters()) {

                is Either.Success -> {

                    val cards = result.value

                    _uiState.update {
                        it.copy(
                            uiState = UiState.Success,
                            characterCards = cards
                        )
                    }
                }

                is Either.Fail -> {
                    _uiState.update { it.copy(uiState = UiState.Error) }
                }

            }

        }
    }

}