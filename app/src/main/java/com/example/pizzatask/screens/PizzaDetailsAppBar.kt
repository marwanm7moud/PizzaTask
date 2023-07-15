package com.example.pizzatask.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzatask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaDetailsAppBar(){
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "Pizza", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            }
        },
        navigationIcon = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                onClick = {},
                shape = CircleShape,
                modifier = Modifier
            ) {
                Icon(
                    tint = Color.Black,
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Navigation Back"
                )
            }
        },
        actions = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                onClick = {},
                shape = CircleShape,
                modifier = Modifier,
            ) {
                Icon(
                    tint = Color.Black,
                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                    contentDescription = "Favourite"
                )
            }
        }
    )
}