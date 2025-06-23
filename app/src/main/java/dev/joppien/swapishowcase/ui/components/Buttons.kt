package dev.joppien.swapishowcase.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.joppien.swapishowcase.ui.theme.spacing

@Composable
fun NavigationButton(textId: Int, onClick: () -> Unit) =
    Button(
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.paddingButtonTopBottom,
                bottom = MaterialTheme.spacing.paddingButtonTopBottom,
                start = MaterialTheme.spacing.paddingButtonSides,
                end = MaterialTheme.spacing.paddingButtonSides,
            ),
            text = stringResource(textId),
            style = MaterialTheme.typography.labelLarge
        )
    }