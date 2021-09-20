package com.example.mobicatestapp.core.resources.theme

import android.annotation.SuppressLint
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.example.mobicatestapp.core.resources.values.*

@SuppressLint("ConflictingOnColor")
internal val darkColorPalette = darkColors(
    primary = blackTopBar,
    primaryVariant = blackTopBar,
    onPrimary = white,
    secondary = white,
    secondaryVariant = mediumGreyBorder,
    onSecondary = whiteDark,
    error = black,
    onError = redLight,
    background = blackBackground,
    onBackground = greyMedium,
    surface = blackSettingsBackground,
    onSurface = darkCard,
)


@SuppressLint("ConflictingOnColor")
internal val lightColorPalette = lightColors(
    primary = greySettingsLight,
    primaryVariant = greySettingsLight,
    onPrimary = black,
    secondary = greyMedium,
    secondaryVariant = lightGreyBorder,
    onSecondary = greyMedium,
    error = lightGreyBorder,
    onError = red,
    background = white,
    onBackground = greyEyeLight,
    surface = greySettingsLight,
    onSurface = greySettingsLight
)