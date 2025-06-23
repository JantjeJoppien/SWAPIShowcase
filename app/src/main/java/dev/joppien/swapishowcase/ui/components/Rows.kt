package dev.joppien.swapishowcase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.joppien.swapishowcase.ui.theme.spacing

@Composable
fun EntryRow(labelString: String, entryString: String) =
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = labelString,
            style = MaterialTheme.typography.bodyLarge,
        )
        HighlightedText(text = entryString)
    }

@Composable
fun EntryListRow(labelString: String, entryList: List<String>) =
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = labelString,
            style = MaterialTheme.typography.bodyLarge,
        )
        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingEntryList)) {
            entryList.forEach { entry ->
                HighlightedText(text = entry)
            }
        }
    }