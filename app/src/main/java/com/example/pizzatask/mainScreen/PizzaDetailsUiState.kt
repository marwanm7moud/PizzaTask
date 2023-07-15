package com.example.pizzatask.mainScreen

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PizzaDetailsUiState(
    val selectedPizza: PizzaSizes = PizzaSizes.Small
)

enum class PizzaSizes(val title: String ,val size: Dp) {
    Small("S" , 165.dp),
    Medium("M" , 190.dp),
    Large("L" , 230.dp)
}