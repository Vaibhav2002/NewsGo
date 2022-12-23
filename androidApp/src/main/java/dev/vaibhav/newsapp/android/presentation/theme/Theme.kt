package dev.vaibhav.newsapp.android.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val lightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC5)
)

val darkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC5)
)

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isDynamicColorAvailable = Build.VERSION.SDK_INT > Build.VERSION_CODES.S
    val colorScheme = when {
        isDynamicColorAvailable && darkTheme -> dynamicDarkColorScheme(context)
        isDynamicColorAvailable -> dynamicLightColorScheme(context)
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}