package com.example.mobicatestapp.core.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.mobicatestapp.R
import com.example.mobicatestapp.constants.ArgumentConstants.ARG_CARD_ID
import com.example.mobicatestapp.constants.NumberConstants.EMPTY_NUMBER_LONG
import com.example.mobicatestapp.constants.ScreenConstants.DETAILS_SCREEN
import com.example.mobicatestapp.constants.ScreenConstants.HOME_SCREEN
import com.example.mobicatestapp.constants.ScreenConstants.SPLASH_SCREEN
import com.example.mobicatestapp.ui.card_details.CardDetailsScreen
import com.example.mobicatestapp.ui.home.HomeScreen
import com.example.mobicatestapp.ui.splash.SplashScreen
import com.example.mobicatestapp.viewmodel.card_details_view_model.CardDetailsViewModel
import com.example.mobicatestapp.viewmodel.home_view_model.HomeViewModel
import com.example.mobicatestapp.viewmodel.main_view_model.MainViewModel
import com.example.mobicatestapp.viewmodel.splash_view_model.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
fun MobicaNavigation(
    mainViewModel: MainViewModel,
    splashViewModel: SplashViewModel,
    homeViewModel: HomeViewModel,
    cardDetailsViewModel: CardDetailsViewModel,
    isDarkMode: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController = navController) }
    val activity = LocalContext.current as? Activity

    NavHost(navController, startDestination = SPLASH_SCREEN) {
        composable(route = SPLASH_SCREEN) { navBackStackEntry ->
            SplashScreen(
                splashViewModel = splashViewModel,
                openHomeScreen = actions.openHomeScreen,
                navigateUpFinish = { activity?.finish() }
            )
        }
        composable(route = HOME_SCREEN) { navBackStackEntry ->
            HomeScreen(
                title = stringResource(id = R.string.screen_home),
                homeViewModel = homeViewModel,
                navigateUpFinish = { activity?.finish() },
                openCardDetailsScreen = actions.openCardDetailsScreen
            )
        }
        composable(
            route = "$DETAILS_SCREEN/{$ARG_CARD_ID}",
            arguments = listOf(navArgument(ARG_CARD_ID) { type = NavType.LongType })
        ) { navBackStackEntry ->
            val cardId = navBackStackEntry
                .arguments
                ?.getLong(ARG_CARD_ID) ?: EMPTY_NUMBER_LONG

            CardDetailsScreen(
                cardId = cardId,
                cardDetailsViewModel = cardDetailsViewModel,
                onBack = actions.navigateUp
            )
        }
    }
}