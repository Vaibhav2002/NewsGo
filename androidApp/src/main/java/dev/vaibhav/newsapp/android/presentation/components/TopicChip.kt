@file:OptIn(ExperimentalMaterial3Api::class)

package dev.vaibhav.newsapp.android.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vaibhav.newsapp.domain.Topic

fun LazyListScope.topicChips(
    topics: List<Topic>,
    selectedTopic: Topic,
    onTopicChanged: (Topic) -> Unit
) {
    items(topics) {
        TopicChip(
            isSelected = it == selectedTopic,
            topic = it,
            onClick = onTopicChanged
        )
    }
}

@Composable
fun TopicChip(
    isSelected: Boolean,
    topic: Topic,
    onClick: (Topic) -> Unit
) {
    ElevatedFilterChip(
        shape = RoundedCornerShape(8.dp),
        selected = isSelected,
        label = {
            Text(
                text = topic.topic,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
            )
        },
        onClick = { onClick(topic) }
    )
}