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
    val paddingLabelTopBottom: Dp = 8.dp,
    val paddingLabelSides: Dp = 16.dp,
    val paddingScreenTopBottom: Dp = 16.dp,
    val paddingScreenSides: Dp = 24.dp,
    val paddingCardTopBottom: Dp = 12.dp,
    val paddingCardSides: Dp = 8.dp,

    val spacingButtonsColumn: Dp = 24.dp,
    val spacingButtonsRow: Dp = 12.dp,
    val spacingEntryColumn: Dp = 16.dp,
    val spacingEntryList: Dp = 8.dp,
    val spacingCardGrid: Dp = 8.dp,
    val spacingCard: Dp = 4.dp,

    val labelCornerRadius: Dp = 18.dp,

    val gridMinSize: Dp = 160.dp,
)

val LocalSpacing = staticCompositionLocalOf { AppSpacing() }

val MaterialTheme.spacing: AppSpacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current