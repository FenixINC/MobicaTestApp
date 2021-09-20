package com.example.mobicatestapp.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.mobicatestapp.constants.StringConstants.EMPTY_TEXT
import com.example.mobicatestapp.core.resources.theme.MobicaMaterialTheme
import com.example.mobicatestapp.core.resources.values.headerScreenSize
import com.example.mobicatestapp.core.resources.values.oneLine
import com.example.mobicatestapp.core.resources.values.white
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun MobicaTopBar(
    title: String = EMPTY_TEXT,
    isHomeScreen: Boolean = false,
    navigateUp: () -> Unit = {},
) {
    Column {
        Spacer(
            modifier = Modifier
                .background(color = MobicaMaterialTheme.colors.primaryVariant)
                .fillMaxWidth()
                .statusBarsPadding()
        )
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = headerScreenSize,
                    maxLines = oneLine,
                    overflow = TextOverflow.Ellipsis
                )
            },
            backgroundColor = MobicaMaterialTheme.colors.primary,
            navigationIcon = when (isHomeScreen) {
                true -> {
                    null
                }
                else -> {
                    {
                        IconButton(onClick = navigateUp) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Icon Back",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
            },
            actions = {
                // TODO: implement settings (e.g. for dark mode)
            }
        )
    }
}