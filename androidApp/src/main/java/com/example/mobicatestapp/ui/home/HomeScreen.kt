package com.example.mobicatestapp.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.constants.NumberConstants.EMPTY_NUMBER
import com.example.mobicatestapp.constants.StringConstants
import com.example.mobicatestapp.core.resources.values.dp16
import com.example.mobicatestapp.core.resources.values.dp8
import com.example.mobicatestapp.database.entity.AttributeEntity
import com.example.mobicatestapp.database.entity.DescriptionEntity
import com.example.mobicatestapp.database.entity.ImageEntity
import com.example.mobicatestapp.database.entity.TitleEntity
import com.example.mobicatestapp.network.response.CardType
import com.example.mobicatestapp.ui.base.ContentScreen
import com.example.mobicatestapp.viewmodel.home_view_model.HomeContract
import com.example.mobicatestapp.viewmodel.home_view_model.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
fun HomeScreen(
    title: String,
    homeViewModel: HomeViewModel,
    navigateUpFinish: () -> Unit,
    openCardDetailsScreen: (Long) -> Unit
) {
    BackHandler(onBack = { navigateUpFinish() })

    homeViewModel.setEvent(event = HomeContract.HomeEvent.LoadHomeList)

    val state = homeViewModel.viewState.collectAsState()

    HomeContent(
        title = title,
        homeViewModel = homeViewModel,
        state = state.value,
        effectFlow = homeViewModel.effect,
        openCardDetailsScreen = openCardDetailsScreen
    )
}

@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
private fun HomeContent(
    title: String,
    homeViewModel: HomeViewModel,
    state: HomeContract.HomeState,
    effectFlow: Flow<HomeContract.HomeEffect>,
    openCardDetailsScreen: (Long) -> Unit,
) {
    LaunchedEffect(key1 = "key_home_content") {
        effectFlow
            .onEach { homeEffect ->
                when (homeEffect) {
                    is HomeContract.HomeEffect.ShowToast -> {
                        // TODO: Show Toast
                    }
                    is HomeContract.HomeEffect.ShowSnackBar -> {
                        // TODO: Show SnackBar
                    }
                    is HomeContract.HomeEffect.Navigation -> {
                        when (homeEffect) {
                            is HomeContract.HomeEffect.Navigation.DetailsScreen -> {
                                openCardDetailsScreen(homeEffect.itemId)
                            }
                        }
                    }
                }
            }
            .collect()
    }

    ContentScreen(
        title = title,
        isHomeScreen = true
    ) {
        val pagerState = rememberPagerState(pageCount = state.result?.size ?: EMPTY_NUMBER)

        /**
         * [HorizontalPager] - Accompanist horizontal pager implementation
         **/

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            if (!state.isLoading) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = dp16),
                    itemSpacing = dp8
                ) { page ->
                    val cardList = state.result ?: emptyList()
                    val card = cardList[page]

                    Column(
                        Modifier
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page)
                                    .absoluteValue

                                lerp(
                                    start = ScaleFactor(scaleX = 1f, scaleY = 0.55f),
                                    stop = ScaleFactor(scaleX = 1f, scaleY = 1f),
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale.scaleX
                                    scaleY = scale.scaleY
                                }

                                alpha = lerp(
                                    start = ScaleFactor(scaleX = 0.1f, scaleY = 0.5f),
                                    stop = ScaleFactor(scaleX = 1f, scaleY = 1f),
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).scaleX
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ListItemCard(
                            card = card,
                            isShimmerVisible = state.isLoading,
                            onItemClick = { itemId ->
                                openCardDetailsScreen(itemId)
                            }
                        )
                    }
                }
            } else {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = dp16),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val cardList = mutableListOf(
                        CardEntity(
                            id = 0L,
                            cardType = CardType.TEXT.name.lowercase(),
                            value = StringConstants.EMPTY_TEXT,
                            attributeEntity = AttributeEntity(),
                            descriptionEntity = DescriptionEntity(),
                            titleEntity = TitleEntity(),
                            imageEntity = ImageEntity()
                        )
                    )

                    items(items = cardList) { card ->
                        ListItemCard(
                            card = card,
                            isShimmerVisible = state.isLoading
                        )
                    }
                }
            }
        }
    }
}