package dev.joppien.swapishowcase.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.NavBackStackEntry

object AppAnimations {

    fun AnimatedContentTransitionScope<NavBackStackEntry>.customScaleIn(): EnterTransition {
        return scaleIn(initialScale = 0.8f, animationSpec = tween(1500))
    }


    fun AnimatedContentTransitionScope<NavBackStackEntry>.customScaleOut(): ExitTransition {
        return scaleOut(targetScale = 1.2f, animationSpec = tween(1500))
    }

    fun customFadeIn(): EnterTransition = fadeIn(animationSpec = tween(durationMillis = 350))
    fun customFadeOut(): ExitTransition = fadeOut(animationSpec = tween(durationMillis = 350))
}