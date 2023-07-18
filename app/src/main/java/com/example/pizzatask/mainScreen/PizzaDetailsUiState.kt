package com.example.pizzatask.mainScreen

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pizzatask.R

data class PizzaDetailsUiState(
    val selectedPizza: PizzaSizes = PizzaSizes.Small,
    val selectedBread: BreadUiState = BreadUiState(),
    val breadsUiState: List<BreadUiState> = emptyList()
)

data class BreadUiState(
    val breadImage: Int = 0,
    val selectedIngredients: List<Ingredient> = emptyList()
)

enum class PizzaSizes(val title: String, val size: Int) {
    Small("S", 165),
    Medium("M", 190),
    Large("L", 230)
}

enum class Ingredient(val defaultImage: Int) {
    Basil(R.drawable.basil_1),
    Broccoli(R.drawable.broccoli_1),
    Mushroom(R.drawable.mushroom_1),
    Onion(R.drawable.onion_1),
    Sausage(R.drawable.sausage_1),
}

