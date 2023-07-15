package com.example.pizzatask.mainScreen.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzatask.mainScreen.PizzaSizes

@Composable
fun PizzaDetailsPizzaSizesChips(
    selectedPizzaSize: PizzaSizes,
    onSelected: (PizzaSizes) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp , alignment = Alignment.CenterHorizontally)) {
        PizzaSizes.values().forEach {
            PizzaSizeChip(
                pizzaSize = it,
                selectedPizzaSize = selectedPizzaSize,
                onSelected = onSelected
            )
        }
    }
}

@Composable
fun PizzaSizeChip(
    pizzaSize: PizzaSizes,
    selectedPizzaSize: PizzaSizes,
    onSelected: (PizzaSizes) -> Unit
) {
    val isSelected = (pizzaSize == selectedPizzaSize)
    val shadowElevation by animateDpAsState(if (isSelected) 8.dp else 0.dp)

    Surface(
        shadowElevation = shadowElevation,
        shape = CircleShape,
        modifier = Modifier
            .size(45.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onSelected(pizzaSize) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = pizzaSize.title,

                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}