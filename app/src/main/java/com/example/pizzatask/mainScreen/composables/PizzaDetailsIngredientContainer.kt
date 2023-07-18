package com.example.pizzatask.mainScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pizzatask.mainScreen.Ingredient
import com.example.pizzatask.ui.theme.PizzaTaskTheme
import kotlin.random.Random

@Composable
fun IngredientContainer(
    currentIngredient: Ingredient,
) {
    val random = Random(System.currentTimeMillis())

    for (i in 0..9) {
        val x = random.nextInt(0, 130)
        val y = random.nextInt(0, 130)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(currentIngredient.defaultImage).build(),
            modifier = Modifier
                .size(35.dp)
                .offset(x.dp, y.dp),
            contentDescription = "Bread"
        )
    }


}


@Composable
@Preview
fun IngredientContainerPreview() {
    PizzaTaskTheme {
        Box(modifier = Modifier.size(165.dp)) {
            IngredientContainer(Ingredient.Broccoli)
        }
    }
}