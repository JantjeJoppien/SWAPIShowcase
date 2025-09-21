package dev.joppien.swapishowcase.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object AppAnimations {

    fun customScaleIn(): EnterTransition {
        return scaleIn(initialScale = 0.8f, animationSpec = tween(1500))
    }

    fun customScaleOut(): ExitTransition {
        return scaleOut(targetScale = 1.2f, animationSpec = tween(1500))
    }

    fun slideInFromRight(): EnterTransition {
        return slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500))
    }

    fun slideInFromLeft(): EnterTransition {
        return slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500))
    }

    fun slideOutToLeft(): ExitTransition {
        return slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
    }

    fun slideOutToRight(): ExitTransition {
        return slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500))
    }

    fun customFadeIn(): EnterTransition = fadeIn(animationSpec = tween(durationMillis = 500))
    fun customFadeOut(): ExitTransition = fadeOut(animationSpec = tween(durationMillis = 500))
}