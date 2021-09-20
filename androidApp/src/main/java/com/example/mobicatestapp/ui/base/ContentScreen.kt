package com.example.mobicatestapp.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun ContentScreen(
    title: String,
    isHomeScreen: Boolean = false,
    onBack: () -> Unit? = {},
    content: @Composable () -> Unit,
) {
    ProvideWindowInsets {
        Column {
            Scaffold(
                modifier = Modifier.weight(weight = 1f),
                topBar = {
                    MobicaTopBar(
                        title = title,
                        isHomeScreen = isHomeScreen,
                        navigateUp = { onBack() }
                    )
                },
                content = { content() }
            )
            Spacer(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.primaryVariant)
                    .fillMaxWidth()
                    .navigationBarsPadding()
            )
        }
    }
}