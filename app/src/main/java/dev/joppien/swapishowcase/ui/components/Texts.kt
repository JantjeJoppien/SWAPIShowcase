package dev.joppien.swapishowcase.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import dev.joppien.swapishowcase.ui.theme.spacing

@Composable
fun HighlightedText(modifier: Modifier = Modifier, text: String) = Text(
    modifier = modifier
        .background(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(size = spacing.labelCornerRadius)
        )
        .padding(
            start = spacing.paddingLabelSides,
            end = spacing.paddingLabelSides,
            top = spacing.paddingLabelTopBottom,
            bottom = spacing.paddingLabelTopBottom,
        ),
    text = text,
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onSecondaryContainer,
    textAlign = TextAlign.Center,
)