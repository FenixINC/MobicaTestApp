package com.example.mobicatestapp.core.resources.theme

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import com.example.mobicatestapp.core.resources.values.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MobicaTheme(
    isDarkThemeFromSystem: Boolean = isSystemInDarkTheme(),
    isDarkThemeFromApp: Boolean = false,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalPaddings provides Paddings(),
        LocalElevations provides Elevations(card = 8.dp)
    ) {
        MaterialTheme(
            colors = if (isDarkThemeFromApp) {
                darkColorPalette
            } else {
                lightColorPalette
            },
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}