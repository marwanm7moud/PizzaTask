package com.example.pizzatask.mainScreen

import androidx.lifecycle.ViewModel
import com.example.pizzatask.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PizzaDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<PizzaDetailsUiState>(PizzaDetailsUiState())
    val state: StateFlow<PizzaDetailsUiState> = _state
    init {
        setup()
    }
    private fun setup(){
        val breadsUiState : List<BreadUiState> =  breads().map {
            BreadUiState(
                breadImage = it
            )
        }
        _state.update {
            it.copy(
                breadsUiState = breadsUiState
            )
        }
    }

    fun onChangePizzaSize(newSelectedPizza: PizzaSizes) {
        _state.update { it.copy(selectedPizza = newSelectedPizza) }
    }

    private fun breads(): List<Int> {
        return listOf(
            R.drawable.bread_1,
            R.drawable.bread_2,
            R.drawable.bread_3,
            R.drawable.bread_4,
            R.drawable.bread_5,
        )
    }
}

