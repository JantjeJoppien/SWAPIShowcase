package dev.joppien.swapishowcase.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppSpacing(
    val paddingButtonTopBottom: Dp = 8.dp,
    val paddingButtonSides: Dp = 16.dp,

    val spacingButtonsColumn: Dp = 24.dp,
    val spacingButtonsRow: Dp = 12.dp,
)

val LocalSpacing = staticCompositionLocalOf { AppSpacing() }

val MaterialTheme.spacing: AppSpacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current