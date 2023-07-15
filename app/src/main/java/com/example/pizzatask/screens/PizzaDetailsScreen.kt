package com.example.pizzatask.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.pizzatask.R
import com.example.pizzatask.ui.theme.PizzaTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaDetailsScreen() {

    Scaffold(
        topBar = {
            PizzaDetailsAppBar()
        }
    ) { paddingValues ->
        ConstraintLayout(modifier = Modifier.padding(PaddingValues(top = paddingValues.calculateTopPadding() + 16.dp))) {
            val (image, breadPager) = createRefs()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(image) {

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
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                }
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