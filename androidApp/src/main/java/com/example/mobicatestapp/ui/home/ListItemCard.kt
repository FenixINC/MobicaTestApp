package com.example.mobicatestapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.mobicatestapp.R
import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.core.resources.values.*
import com.example.mobicatestapp.network.response.CardType
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListItemCard(
    card: CardEntity,
    isShimmerVisible: Boolean,
    onItemClick: (Long) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(width = dp300)
            .wrapContentHeight()
            .clip(RoundedCornerShape(size = dp16))
            .background(color = Color.Gray)
            .clickable(onClick = { onItemClick(card.id) })
            .placeholder(
                visible = isShimmerVisible,
                color = Color.Gray,
                highlight = PlaceholderHighlight.shimmer(highlightColor = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(corner = CornerSize(dp16))
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Box(
                modifier = Modifier.size(width = dp300, height = dp400),
                contentAlignment = Alignment.Center
            ) {
                if (card.imageEntity.url.isEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_empty_image),
                        contentDescription = "Empty card image",
                        modifier = Modifier.size(width = dp100, height = dp100),
                        tint = Color.White
                    )
                } else {
                    Image(
                        painter = rememberImagePainter(
                            data = card.imageEntity.url,
                            builder = {
                                transformations(RoundedCornersTransformation(radius = radius16F))
                            }
                        ),
                        contentDescription = "Card image",
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = dp8)
            ) {
                when (card.cardType) {
                    CardType.TEXT.name.lowercase() -> {
                        val textSize = if (card.attributeEntity.fontSize.isNotEmpty()) {
                            card.attributeEntity.fontSize.toInt().sp
                        } else {
                            cardHeaderSize
                        }
                        // TODO: need to change color on a backend, it's impossible to read
                        val textColor = if (card.attributeEntity.textColor.isNotEmpty()) {
                            Color(android.graphics.Color.parseColor(card.attributeEntity.textColor))
                        } else {
                            white
                        }
                        Text(
                            text = card.value,
                            fontSize = textSize,
                            color = white,
                            modifier = Modifier
                                .width(width = dp300)
                                .wrapContentHeight()
                        )
                    }
                    CardType.TITLE_DESCRIPTION.name.lowercase() -> {
                        val titleTextSize = if (card.titleEntity.fontSize.isNotEmpty()) {
                            card.titleEntity.fontSize.toInt().sp
                        } else {
                            cardHeaderSize
                        }
                        // TODO: need to change color on a backend, it's impossible to read
                        val titleTextColor = if (card.titleEntity.textColor.isNotEmpty()) {
                            Color(android.graphics.Color.parseColor(card.titleEntity.textColor))
                        } else {
                            white
                        }

                        val descriptionTextSize =
                            if (card.descriptionEntity.fontSize.isNotEmpty()) {
                                card.descriptionEntity.fontSize.toInt().sp
                            } else {
                                cardHeaderSize
                            }
                        // TODO: need to change color on a backend, it's impossible to read
                        val descriptionTextColor =
                            if (card.descriptionEntity.textColor.isNotEmpty()) {
                                Color(android.graphics.Color.parseColor(card.descriptionEntity.textColor))
                            } else {
                                white
                            }

                        Column(modifier = Modifier.padding(bottom = dp4)) {
                            Text(
                                text = card.titleEntity.value,
                                fontSize = titleTextSize,
                                color = white,
                                modifier = Modifier.wrapContentSize()
                            )

                            Text(
                                text = card.descriptionEntity.value,
                                fontSize = descriptionTextSize,
                                color = white,
                                modifier = Modifier.wrapContentSize()
                            )
                        }
                    }
                    CardType.IMAGE_TITLE_DESCRIPTION.name.lowercase() -> {
                        val titleTextSize = if (card.titleEntity.fontSize.isNotEmpty()) {
                            card.titleEntity.fontSize.toInt().sp
                        } else {
                            30.sp
                        }
                        val titleTextColor = if (card.titleEntity.textColor.isNotEmpty()) {
                            Color(android.graphics.Color.parseColor(card.titleEntity.textColor))
                        } else {
                            white
                        }

                        val descriptionTextSize =
                            if (card.descriptionEntity.fontSize.isNotEmpty()) {
                                card.descriptionEntity.fontSize.toInt().sp
                            } else {
                                cardHeaderSize
                            }
                        val descriptionTextColor =
                            if (card.descriptionEntity.textColor.isNotEmpty()) {
                                Color(android.graphics.Color.parseColor(card.descriptionEntity.textColor))
                            } else {
                                white
                            }

                        Column(modifier = Modifier.padding(bottom = dp4)) {
                            Text(
                                text = card.titleEntity.value,
                                fontSize = titleTextSize,
                                color = titleTextColor,
                                modifier = Modifier.wrapContentSize()
                            )

                            Text(
                                text = card.descriptionEntity.value,
                                fontSize = descriptionTextSize,
                                color = descriptionTextColor,
                                modifier = Modifier.wrapContentSize()
                            )
                        }
                    }
                }
            }
        }
    }
}