package com.tableauelec.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = BleuElectrique,
    secondary = AccentOrange,
    background = FondClair,
    surface = FondCarte,
    onBackground = TexteFonce,
    onSurface = TexteFonce
)

private val DarkColors = darkColorScheme(
    primary = BleuElectrique,
    secondary = AccentOrange
)

@Composable
fun TableauElectriqueTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
