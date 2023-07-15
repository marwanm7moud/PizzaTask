package com.example.pizzatask.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pizzatask.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaDetailsBreadPager(modifier: Modifier , breadSize:Dp = 165.dp) {
    val breads = listOf(
        R.drawable.bread_1,
        R.drawable.bread_2,
        R.drawable.bread_3,
        R.drawable.bread_4,
        R.drawable.bread_5,
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        breads.size
    }
    HorizontalPager(
        state = pagerState ,
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) { index ->
        Box(modifier = Modifier.fillMaxWidth() , contentAlignment = Alignment.Center) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(breads[index]).build(),
                modifier = Modifier.size(breadSize),
                contentDescription = "Bread"
            )
        }
    }
}