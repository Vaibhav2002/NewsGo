package dev.vaibhav.newsapp.android.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    isBackEnabled: Boolean = false,
    scrollBehavior: TopAppBarScrollBehavior,
    textStyle: TextStyle = MaterialTheme.typography.displaySmall,
    buttons: List<AppBarButton> = emptyList(),
    onBackPress: () -> Unit = {}
) {
    LargeTopAppBar(
        title = { Text(text = title, style = textStyle) },
        modifier = modifier,
        navigationIcon = {
            if (isBackEnabled)
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onBackPress() }
                        .padding(8.dp)
                )
        },
        actions = {
            buttons.forEach { AppBarButton(appBarButton = it) }
        },
        scrollBehavior = scrollBehavior
    )
}