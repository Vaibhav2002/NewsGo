package dev.vaibhav.newsapp.android.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun SaveToggleButton(
    modifier:Modifier = Modifier,
    isSaved:Boolean,
    shape:Shape = CircleShape,
    onClick:(Boolean)->Unit
) {
    FilledIconToggleButton(
        modifier = modifier,
        checked = isSaved,
        onCheckedChange = onClick,
        shape = shape,
        colors = IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
            contentColor = Color.Gray,
            checkedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Icon(
            imageVector = Icons.Rounded.Favorite,
            contentDescription = "Save",
            modifier = Modifier.fillMaxSize(0.5f)
        )
    }
}