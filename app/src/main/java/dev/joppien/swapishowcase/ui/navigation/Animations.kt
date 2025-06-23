package dev.joppien.swapishowcase.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

object AppAnimations {

    fun AnimatedContentTransitionScope<NavBackStackEntry>.customScaleIn(): EnterTransition {
        return scaleIn(initialScale = 0.8f, animationSpec = tween(1500))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.customScaleOut(): ExitTransition {
        return scaleOut(targetScale = 1.2f, animationSpec = tween(1500))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInFromRight(): EnterTransition {
        return slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInFromLeft(): EnterTransition {
        return slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutToLeft(): ExitTransition {
        return slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
    }

    fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutToRight(): ExitTransition {
        return slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500))
    }

    fun customFadeIn(): EnterTransition = fadeIn(animationSpec = tween(durationMillis = 500))
    fun customFadeOut(): ExitTransition = fadeOut(animationSpec = tween(durationMillis = 500))
}