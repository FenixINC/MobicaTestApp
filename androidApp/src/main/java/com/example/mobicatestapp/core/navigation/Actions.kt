package com.example.mobicatestapp.core.navigation

import androidx.navigation.NavHostController
import com.example.mobicatestapp.constants.ScreenConstants.DETAILS_SCREEN
import com.example.mobicatestapp.constants.ScreenConstants.HOME_SCREEN

class Actions(navController: NavHostController) {
    val openHomeScreen: () -> Unit = {
        navController.navigate(route = HOME_SCREEN)
    }
    val openCardDetailsScreen: (Long) -> Unit = { itemId ->
        navController.navigate(route = "$DETAILS_SCREEN/$itemId")
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}