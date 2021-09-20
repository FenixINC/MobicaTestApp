package com.example.mobicatestapp.ui.card_details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mobicatestapp.R
import com.example.mobicatestapp.core.resources.values.dp100
import com.example.mobicatestapp.core.resources.values.dp400
import com.example.mobicatestapp.network.response.CardType
import com.example.mobicatestapp.ui.base.ContentScreen
import com.example.mobicatestapp.viewmodel.card_details_view_model.CardDetailsContract
import com.example.mobicatestapp.viewmodel.card_details_view_model.CardDetailsViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun CardDetailsScreen(
    cardId: Long,
    cardDetailsViewModel: CardDetailsViewModel,
    onBack: () -> Unit
) {
    BackHandler(onBack = { onBack() })

    cardDetailsViewModel.setEvent(
        event = CardDetailsContract.CardDetailsEvent.LoadCardDetails(cardId = cardId)
    )

    val state = cardDetailsViewModel.viewState.collectAsState()

    CardDetailsContent(
        state = state.value,
        effectFlow = cardDetailsViewModel.effect,
        onBack = onBack
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun CardDetailsContent(
    state: CardDetailsContract.CardDetailsState,
    effectFlow: Flow<CardDetailsContract.CardDetailsEffect>,
    onBack: () -> Unit
) {
    LaunchedEffect(key1 = "key_card_details") {
        effectFlow
            .onEach { cardDetailsEffect ->
                when (cardDetailsEffect) {
                    is CardDetailsContract.CardDetailsEffect.ShowToast -> {
                        // TODO: Show Toast
                    }
                    is CardDetailsContract.CardDetailsEffect.ShowSnackBar -> {
                        // TODO: Show SnackBar
                    }
                }
            }
            .collect()
    }

    ContentScreen(
        title = when (state.cardEntity.cardType) {
            CardType.TEXT.name.lowercase() -> {
                state.cardEntity.value
            }
            CardType.TITLE_DESCRIPTION.name.lowercase(), CardType.IMAGE_TITLE_DESCRIPTION.name.lowercase() -> {
                state.cardEntity.titleEntity.value
            }
            else -> {
                state.cardEntity.titleEntity.value
            }
        },
        onBack = onBack
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.cardEntity.imageEntity.url.isNotEmpty()) {
                Image(
                    painter = rememberImagePainter(
                        data = state.cardEntity.imageEntity.url
                    ),
                    contentDescription = "Card image",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier.wrapContentSize()
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = dp400)
                        .background(color = Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_empty_image),
                        contentDescription = "Empty card image",
                        modifier = Modifier.size(width = dp100, height = dp100),
                        tint = Color.White
                    )
                }
            }
        }
    }
}