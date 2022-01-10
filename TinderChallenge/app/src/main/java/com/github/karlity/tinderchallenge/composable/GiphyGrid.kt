package com.github.karlity.tinderchallenge.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.github.karlity.tinderchallenge.R
import com.github.karlity.tinderchallenge.challenge.ChallengeAction
import com.github.karlity.tinderchallenge.challenge.ChallengeViewModel
import timber.log.Timber

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun GiphyGrid(
    viewModel: ChallengeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val density = LocalDensity.current

    var isSearchBarActive by remember { mutableStateOf(false) }

    var searchBarTextInput by remember { mutableStateOf<String?>(null) }

    val state = viewModel.stateFlow.collectAsState()

    SideEffect {
        viewModel.postAction(ChallengeAction.Load)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(5.dp)
    ) {

        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Navigate Back",
                        modifier = Modifier.size(35.dp)
                    )
                }

                this.AnimatedVisibility(
                    visible = isSearchBarActive,
                    enter = slideInHorizontally(initialOffsetX = { it * 2 }),
                    exit = slideOutHorizontally(
                        targetOffsetX = { with(density) { (it).dp.roundToPx() } },
                        animationSpec = tween(durationMillis = 3000, easing = LinearOutSlowInEasing)
                    ),
                    modifier = Modifier.clipToBounds()
                ) {
                    OutlinedTextField(
                        value = searchBarTextInput ?: "",
                        onValueChange = {
                            Timber.wtf("string value: $it")
                            viewModel.postAction(ChallengeAction.Search(it))
                            searchBarTextInput = it
                        },
                        maxLines = 12,
                        placeholder = {
                            Text(
                                text = "Search..",
                                color = Color.Gray
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 16.sp
                        ),
                        colors =
                        TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            backgroundColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color.LightGray, RoundedCornerShape(15.dp))

                    )
                }
                Box {
                    IconButton(onClick = { isSearchBarActive = !isSearchBarActive }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(0.dp)
                        )
                    }
                }
            }
            LazyVerticalGrid(cells = GridCells.Adaptive(140.dp)) {
                if (state.value.gifList != null) {
                    state.value.gifList?.map {
                        item {
                            Timber.i("wtf ${it.embedUrl}")
                            Image(
                                painter = rememberImagePainter(data = it.embedUrl, builder = {
                                    scale(Scale.FILL)
                                }),
                                contentDescription = it.title,
                                modifier = Modifier.size(140.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}