package dev.vaibhav.newsapp.android.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    shape:Shape = CircleShape,
    onBackPress:()->Unit
) {
    FilledIconButton(
        onClick = onBackPress,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = modifier.clip(shape)
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            modifier = Modifier.size(24.dp),
            contentDescription = "Back"
        )
    }
}