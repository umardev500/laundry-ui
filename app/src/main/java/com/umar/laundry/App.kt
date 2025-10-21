package com.umar.laundry

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition

@OptIn(ExperimentalVoyagerApi::class)
@Composable
fun App() {
    val initialScreen = Routes.profile()

    Navigator(
        screen = initialScreen, disposeBehavior = NavigatorDisposeBehavior(disposeSteps = true)
    ) { navigator ->
        SlideTransition(
            navigator = navigator, disposeScreenAfterTransitionEnd = true
        )
    }
}