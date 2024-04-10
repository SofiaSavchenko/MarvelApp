package com.example.marvelapp.ui.full_screen.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.network.model.either.Either
import com.example.marvelapp.network.data.repository.Repository
import com.example.marvelapp.network.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class FullCardViewModel(private val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(FullCardUiState())
    val uiState: StateFlow<FullCardUiState> = _uiState.asStateFlow()

    suspend fun observeDataById(id: Int) {

        viewModelScope.launch {

            when (val result = repository.getMarvelCharacterById(id)) {

                is Either.Success -> {

                    val card = result.value

                    _uiState.update {
                        it.copy(
                            uiState = UiState.Success,
                            characterCard = card
                        )
                    }

                }

                is Either.Fail -> _uiState.update { it.copy(uiState = UiState.Error) }

            }

        }
    }

}