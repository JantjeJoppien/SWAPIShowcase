package dev.joppien.swapishowcase.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.joppien.swapishowcase.ui.theme.spacing

@Composable
fun EntryRow(labelString: String, entryString: String) =
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(0.5f),
            text = labelString,
            style = MaterialTheme.typography.bodyLarge,
        )
        HighlightedText(
            modifier = Modifier.weight(0.5f),
            text = entryString,
        )
    }

@Composable
fun EntryListRow(labelString: String, entryList: List<String>) =
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier.weight(0.5f),
            text = labelString,
            style = MaterialTheme.typography.bodyLarge,
        )
        Column(
            modifier = Modifier.weight(0.5f),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacingEntryList),
        ) {
            entryList.forEach { entry ->
                HighlightedText(modifier = Modifier.fillMaxWidth(), text = entry)
            }
        }
    }