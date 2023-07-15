package com.example.pizzatask.mainScreen.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pizzatask.mainScreen.BreadUiState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaDetailsBreadPager(modifier: Modifier, selectedSize: Dp, breadsUiState: List<BreadUiState>) {
    val animatedSize by animateDpAsState(targetValue = selectedSize)
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        breadsUiState.size
    }
    HorizontalPager(
        state = pagerState ,
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) { index ->
        Box(modifier = Modifier.fillMaxWidth() , contentAlignment = Alignment.Center) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(breadsUiState[index].breadImage).build(),
                modifier = Modifier.size(animatedSize),
                contentDescription = "Bread"
            )
        }
    }
}