package com.example.pizzatask.mainScreen.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pizzatask.R
import com.example.pizzatask.mainScreen.BreadUiState
import com.example.pizzatask.mainScreen.Ingredient
import com.example.pizzatask.ui.theme.PizzaTaskTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaDetailsBreadPager(
    modifier: Modifier = Modifier,
    selectedSize: Int,
    breadsUiState: List<BreadUiState>,
    selectedBread: BreadUiState,
    onChangeCurrentBread: (BreadUiState) -> Unit
) {
    val animatedSize by animateDpAsState(targetValue = selectedSize.dp)
    val animatedsScale by animateFloatAsState(targetValue = selectedSize.toFloat()/165f)

    val pagerState = rememberPagerState(
        initialPage = breadsUiState.indexOf(selectedBread),
        initialPageOffsetFraction = 0f
    ) {
        breadsUiState.size
    }
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) { index ->
        onChangeCurrentBread(breadsUiState[pagerState.currentPage])
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(breadsUiState[index].breadImage).build(),
                modifier = Modifier.size(animatedSize),
                contentDescription = "Bread"
            )

            Ingredient.values().forEach{
                AnimatedVisibility(
                    visible = breadsUiState[index].selectedIngredients.contains(it),
                    enter = scaleIn(initialScale = 15f),
                    exit = fadeOut()
                ) {
                    Box(modifier = Modifier.size(165.dp).scale(animatedsScale)) {
                        IngredientContainer(
                            currentIngredient = it,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable

fun PizzaDetailsBreadPagerPreview() {
    val tog = listOf<BreadUiState>(
        BreadUiState(
            breadImage = R.drawable.bread_1,
            selectedIngredients = listOf(
                Ingredient.Onion
            )
        )
    )
    PizzaTaskTheme {
        PizzaDetailsBreadPager(
            selectedSize = 165,
            breadsUiState = tog,
            selectedBread = tog[0]
        ) {}
    }
}