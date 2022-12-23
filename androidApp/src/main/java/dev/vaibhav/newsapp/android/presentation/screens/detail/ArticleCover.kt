package dev.vaibhav.newsapp.android.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.vaibhav.newsapp.android.presentation.components.BackButton

@Composable
fun ArticleCover(
    image: String,
    title: String,
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(0.dp)
            .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Article Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
                ),
        )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                .align(Alignment.BottomCenter)
        )
        BackButton(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart),
            onBackPress = onBackPress
        )
    }
}