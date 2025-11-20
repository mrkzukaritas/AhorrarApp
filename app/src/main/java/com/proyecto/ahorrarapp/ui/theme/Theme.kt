package com.proyecto.ahorrarapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,           // Verde marino principal
    onPrimary = Color.White,          // Texto sobre verde

    secondary = SecondaryGreen,       // Verde medio
    onSecondary = Color.White,        // Texto sobre verde medio

    tertiary = PrimaryBlue,           // Azul real como acento
    onTertiary = Color.White,         // Texto sobre azul

    background = BackgroundWhite,     // Fondo blanco suave
    onBackground = TextPrimary,       // Texto principal

    surface = SurfaceWhite,           // Superficie blanca
    onSurface = TextPrimary,          // Texto sobre superficie

    surfaceVariant = CardGreenLight,  // Variante de superficie verde claro
    onSurfaceVariant = TextSecondary, // Texto sobre variante

    outline = BorderLight,            // Bordes claros
    outlineVariant = Color(0xFFC8E6C9) // Bordes más suaves
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryGreen,
    onPrimary = Color.White,
    secondary = SecondaryGreen,
    onSecondary = Color.White,
    tertiary = PrimaryBlue,
    onTertiary = Color.White,
    background = Color(0xFF121212),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFFFFFFF)
)

@Composable
fun AhorrarAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) DarkColorScheme else LightColorScheme
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}