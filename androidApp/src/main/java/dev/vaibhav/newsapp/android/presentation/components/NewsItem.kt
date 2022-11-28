package dev.vaibhav.newsapp.android.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.utils.DateTimeUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsItem(
    modifier:Modifier = Modifier,
    article: Article,
    onClick:(Article)->Unit
) {
    ElevatedCard(
        onClick = { onClick(article) },
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
    ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = "News Cover Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = article.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = DateTimeUtil.formatDateTime(article.timeStamp),
            modifier = Modifier.align(Alignment.End).padding(end = 16.dp),
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}