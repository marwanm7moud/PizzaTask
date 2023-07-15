package com.example.pizzatask.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.pizzatask.mainScreen.composables.PizzaDetailsPizzaSizesChips
import com.example.pizzatask.ui.theme.PizzaTaskTheme

@Composable
fun PizzaDetailsScreen(
    viewModel: PizzaDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    PizzaDetailsContent(state = state , onChangedPizzaSize = viewModel::onChangePizzaSize)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaDetailsContent(state: PizzaDetailsUiState, onChangedPizzaSize:(PizzaSizes)->Unit) {
    Scaffold(
        topBar = {
            PizzaDetailsAppBar()
        }
    ) { paddingValues ->
        ConstraintLayout(modifier = Modifier.padding(PaddingValues(top = paddingValues.calculateTopPadding() + 16.dp))) {
            val (plate, breadPager, price, pizzaSizes) = createRefs()

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
                selectedSize = state.selectedPizza.size
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
            )

            PizzaDetailsPizzaSizesChips(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .constrainAs(pizzaSizes) {
                        top.linkTo(price.bottom)
                    },
                selectedPizzaSize = state.selectedPizza,
                onSelected = onChangedPizzaSize
            )

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