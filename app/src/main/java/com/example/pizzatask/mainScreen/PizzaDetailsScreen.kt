package com.example.pizzatask.mainScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzatask.R
import com.example.pizzatask.mainScreen.composables.PizzaDetailsAppBar
import com.example.pizzatask.mainScreen.composables.PizzaDetailsBreadPager
import com.example.pizzatask.mainScreen.composables.PizzaDetailsIngredientChips
import com.example.pizzatask.mainScreen.composables.PizzaDetailsPizzaSizesChips
import com.example.pizzatask.ui.theme.PizzaTaskTheme

@Composable
fun PizzaDetailsScreen(
    viewModel: PizzaDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    PizzaDetailsContent(
        state = state,
        currentBreadUiState = state.selectedBread,
        onChangedPizzaSize = viewModel::onChangePizzaSize,
        onChangedIngredient = viewModel::onChangeIngredientChip,
        onChangeCurrentBread = viewModel::onChangeCurrentBread
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaDetailsContent(
    state: PizzaDetailsUiState,
    onChangedPizzaSize: (PizzaSizes) -> Unit,
    onChangedIngredient: (Ingredient) -> Unit,
    onChangeCurrentBread: (BreadUiState) -> Unit,
    currentBreadUiState: BreadUiState,
) {
    Scaffold(
        topBar = {
            PizzaDetailsAppBar()
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier.fillMaxSize().padding(
                PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                )
            )
        ) {
            val (plate, breadPager, price, pizzaSizes, text, ingredients, addToCart) = createRefs()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(plate) {

                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plate),
                    modifier = Modifier.size(275.dp),
                    contentDescription = ""
                )
            }

            PizzaDetailsBreadPager(
                modifier = Modifier.constrainAs(breadPager) {
                    top.linkTo(plate.top)
                    bottom.linkTo(plate.bottom)
                    start.linkTo(plate.start)
                    end.linkTo(plate.end)
                },
                selectedSize = state.selectedPizza.size,
                breadsUiState = state.breadsUiState,
                onChangeCurrentBread = onChangeCurrentBread,
                selectedBread = currentBreadUiState
            )

            Text(
                text = "$17",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .constrainAs(price) {
                        top.linkTo(plate.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .clickable {
                        Log.e("TAG", "onChangeIngredientChip: ${state.breadsUiState}")
                    }
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .constrainAs(pizzaSizes) {
                        top.linkTo(price.bottom)
                    },
            ) {
                PizzaDetailsPizzaSizesChips(
                    modifier = Modifier,
                    selectedPizzaSize = state.selectedPizza,
                    onSelected = onChangedPizzaSize
                )
            }

            Text(
                text = "CUSTOMIZE YOUR PIZZA",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(text) {
                        top.linkTo(pizzaSizes.bottom)
                        start.linkTo(parent.start)
                    }
            )
            PizzaDetailsIngredientChips(
                selectedIngredients = currentBreadUiState.selectedIngredients,
                onSelected = onChangedIngredient,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(ingredients) {
                        top.linkTo(text.bottom)
                    }
            )

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(8.dp),
                onClick = { },
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .constrainAs(addToCart) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Add to Cart")
            }
        }
    }
}

@Preview
@Composable
fun PizzaDetailsScreenPreview() {
    PizzaTaskTheme {
        PizzaDetailsScreen()
    }
}