package com.example.pizzatask.mainScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PizzaDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<PizzaDetailsUiState>(PizzaDetailsUiState())
    val state: StateFlow<PizzaDetailsUiState> = _state

    fun onChangePizzaSize(newSelectedPizza: PizzaSizes) {
        _state.update { it.copy(selectedPizza = newSelectedPizza) }
    }

}

