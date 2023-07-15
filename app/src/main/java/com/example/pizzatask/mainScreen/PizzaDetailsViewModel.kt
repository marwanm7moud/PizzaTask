package com.example.pizzatask.mainScreen

import android.util.Log
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

    private fun setup() {
        val breadsUiState: List<BreadUiState> = breads().map {
            BreadUiState(
                breadImage = it,
            )
        }
        _state.update {
            it.copy(
                breadsUiState = breadsUiState,
                selectedBread = breadsUiState[0]
            )
        }
    }

    fun onChangePizzaSize(newSelectedPizza: PizzaSizes) {
        _state.update { it.copy(selectedPizza = newSelectedPizza) }
    }

    fun onChangeIngredientChip(newSelectedIngredient: Ingredient) {
        val selectedBread = _state.value.selectedBread
        val newSelectedBread =
            newSelectedBreadAfterAddOrRemoveIngredient(newSelectedIngredient, selectedBread)
        val newBreads = newBreadsAfterAddOrRemoveIngredient(newSelectedIngredient, newSelectedBread)

        _state.update {
            it.copy(
                breadsUiState = newBreads,
                selectedBread = newSelectedBread
            )
        }
    }

    private fun newSelectedBreadAfterAddOrRemoveIngredient(
        newSelectedIngredient: Ingredient,
        selectedBread: BreadUiState
    ): BreadUiState {
        val selectedBreadIngredients = selectedBread.selectedIngredients.toMutableList()
        if (selectedBreadIngredients.contains(newSelectedIngredient))
            selectedBreadIngredients.remove(newSelectedIngredient)
        else
            selectedBreadIngredients.add(newSelectedIngredient)

        return BreadUiState(
            breadImage = selectedBread.breadImage,
            selectedIngredients = selectedBreadIngredients,
        )
    }

    private fun newBreadsAfterAddOrRemoveIngredient(
        newSelectedIngredient: Ingredient,
        newSelectedBread: BreadUiState
    ): List<BreadUiState> {
        val breads = _state.value.breadsUiState.toMutableList()
        breads[breads.indexOf(_state.value.selectedBread)] = newSelectedBread
        return breads
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

    fun onChangeCurrentBread(breadUiState: BreadUiState) {
        _state.update { it.copy(selectedBread = breadUiState) }
    }
}

