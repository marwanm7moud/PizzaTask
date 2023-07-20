package com.example.pizzatask.mainScreen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzatask.mainScreen.BreadUiState
import com.example.pizzatask.mainScreen.Ingredient
import com.example.pizzatask.ui.theme.SelectedChip

@Composable
fun PizzaDetailsIngredientChips(
    selectedIngredients: List<Ingredient>,
    onSelected: (Ingredient) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp,)
    ) {
        Ingredient.values().forEach {
            IngredientChip(
                ingredient = it,
                selectedIngredients = selectedIngredients,
                onSelected = onSelected
            )
        }
    }
}

@Composable
fun IngredientChip(
    ingredient: Ingredient,
    selectedIngredients: List<Ingredient>,
    onSelected: (Ingredient) -> Unit,
) {
    val isSelected = selectedIngredients.contains(ingredient)
    Surface(
        color = if (isSelected) SelectedChip else Color.White,
        shape = CircleShape,
        modifier = Modifier
            .size(45.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onSelected(ingredient) },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = ingredient.defaultImage),
                contentDescription = ingredient.name
            )
        }

    }
}