package dev.vaibhav.newsapp.android.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title:String,
    isBackEnabled:Boolean = false,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackPress:()->Unit = {}
) {
    LargeTopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.displaySmall) },
        modifier = modifier,
        navigationIcon = {
            if(isBackEnabled)
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    modifier = modifier.clickable { onBackPress() }
                )
        },
        scrollBehavior = scrollBehavior
    )
}