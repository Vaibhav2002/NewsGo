package dev.vaibhav.newsapp.android.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SaveToggleButton(
    modifier:Modifier = Modifier,
    isSaved:Boolean,
    onClick:(Boolean)->Unit
) {
    FilledIconToggleButton(
        modifier = modifier,
        checked = isSaved,
        onCheckedChange = onClick,
        shape = CircleShape,
        colors = IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = Color.White,
            contentColor = Color.LightGray,
            checkedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.Rounded.Favorite,
            contentDescription = "Save"
        )
    }
}