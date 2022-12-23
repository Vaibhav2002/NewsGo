package dev.vaibhav.newsapp.android.presentation.screens.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.vaibhav.newsapp.android.presentation.components.SaveToggleButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    viewModel: ArticleDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = modifier,
        topBar = {
            ArticleCover(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3),
                image = uiState.image,
                title = uiState.title,
                onBackPress = onBack
            )
        },
        floatingActionButton = {
            SaveArticleButton(
                modifier = Modifier.size(64.dp),
                isSaved = uiState.isSaved,
                onClick = { viewModel.toggleSave() }
            )
        }
    ) {
        ArticleDetailContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            description = uiState.description,
            content = uiState.content,
            url = uiState.url,
            scrollState = scrollState
        )
    }
}

@Composable
fun ArticleDetailContent(
    description: String,
    content: String,
    url: String,
    modifier: Modifier = Modifier,
    scrollState: ScrollState
) {
    val uriHandler = LocalUriHandler.current
    val content = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
            )
        ) {
            append(content.substringBeforeLast("["))
        }

        pushStringAnnotation("Read More", annotation = "Read More")
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Read More")
        }
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(scrollState),
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        ClickableText(
            text = content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.8f),
        ) {
            content.getStringAnnotations("Read More", it, it)
                .firstOrNull()?.also {
                    uriHandler.openUri(url)
                }
        }
    }
}

@Composable
fun SaveArticleButton(
    modifier: Modifier = Modifier,
    isSaved: Boolean,
    onClick: (Boolean) -> Unit
) {
    var modifier = modifier
    if (!isSystemInDarkTheme())
        modifier = modifier.shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(12.dp),
            spotColor = if (!isSaved) DefaultShadowColor
            else MaterialTheme.colorScheme.primary
        )
    SaveToggleButton(
        modifier = modifier,
        isSaved = isSaved,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp)
    )
}