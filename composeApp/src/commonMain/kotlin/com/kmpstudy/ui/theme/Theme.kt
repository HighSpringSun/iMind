package com.mywf.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.kmpstudy.ui.theme.onPrimaryContainerDarkHighContrast
import com.kmpstudy.ui.theme.onPrimaryContainerLightMediumContrast
import com.kmpstudy.ui.theme.onPrimaryDarkHighContrast
import com.kmpstudy.ui.theme.onPrimaryLightMediumContrast
import com.kmpstudy.ui.theme.primaryContainerDarkHighContrast
import com.kmpstudy.ui.theme.primaryContainerLightMediumContrast
import com.kmpstudy.ui.theme.primaryDarkHighContrast
import com.kmpstudy.ui.theme.primaryLightMediumContrast
import com.kmpstudy.ui.theme.surfaceContainerDark
import com.kmpstudy.ui.theme.surfaceContainerHighDark
import com.kmpstudy.ui.theme.surfaceContainerHighestDark
import com.kmpstudy.ui.theme.surfaceContainerLowDark

private val lightScheme = lightColorScheme(
    primary = _root_ide_package_.com.kmpstudy.ui.theme.primaryLight,
    onPrimary = _root_ide_package_.com.kmpstudy.ui.theme.onPrimaryLight,
    primaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.primaryContainerLight,
    onPrimaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.onPrimaryContainerLight,

    secondary = _root_ide_package_.com.kmpstudy.ui.theme.secondaryLight,
    onSecondary = _root_ide_package_.com.kmpstudy.ui.theme.onSecondaryLight,
    secondaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.secondaryContainerLight,
    onSecondaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.onSecondaryContainerLight,

    tertiary = _root_ide_package_.com.kmpstudy.ui.theme.tertiaryLight,
    onTertiary = _root_ide_package_.com.kmpstudy.ui.theme.onTertiaryLight,
    tertiaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.tertiaryContainerLight,
    onTertiaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.onTertiaryContainerLight,

    error = _root_ide_package_.com.kmpstudy.ui.theme.errorLight,
    onError = _root_ide_package_.com.kmpstudy.ui.theme.onErrorLight,
    errorContainer = _root_ide_package_.com.kmpstudy.ui.theme.errorContainerLight,
    onErrorContainer = _root_ide_package_.com.kmpstudy.ui.theme.onErrorContainerLight,

    background = _root_ide_package_.com.kmpstudy.ui.theme.backgroundLight,
    onBackground = _root_ide_package_.com.kmpstudy.ui.theme.onBackgroundLight,

    surface = _root_ide_package_.com.kmpstudy.ui.theme.surfaceLight,
    onSurface = _root_ide_package_.com.kmpstudy.ui.theme.onSurfaceLight,

    surfaceVariant = _root_ide_package_.com.kmpstudy.ui.theme.surfaceVariantLight,
    onSurfaceVariant = _root_ide_package_.com.kmpstudy.ui.theme.onSurfaceVariantLight,

    outline = _root_ide_package_.com.kmpstudy.ui.theme.outlineLight,
    outlineVariant = _root_ide_package_.com.kmpstudy.ui.theme.outlineVariantLight,

    scrim = _root_ide_package_.com.kmpstudy.ui.theme.scrimLight,

    inverseSurface = _root_ide_package_.com.kmpstudy.ui.theme.inverseSurfaceLight,
    inverseOnSurface = _root_ide_package_.com.kmpstudy.ui.theme.inverseOnSurfaceLight,
    inversePrimary = _root_ide_package_.com.kmpstudy.ui.theme.inversePrimaryLight,

    surfaceDim = _root_ide_package_.com.kmpstudy.ui.theme.surfaceDimLight,
    surfaceBright = _root_ide_package_.com.kmpstudy.ui.theme.surfaceBrightLight,

    surfaceContainerLowest = _root_ide_package_.com.kmpstudy.ui.theme.surfaceContainerLowestLight,
    surfaceContainerLow = _root_ide_package_.com.kmpstudy.ui.theme.surfaceContainerLowLight,
    surfaceContainer = _root_ide_package_.com.kmpstudy.ui.theme.surfaceContainerLight,
    surfaceContainerHigh = _root_ide_package_.com.kmpstudy.ui.theme.surfaceContainerHighLight,
    surfaceContainerHighest = _root_ide_package_.com.kmpstudy.ui.theme.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = _root_ide_package_.com.kmpstudy.ui.theme.primaryDark,
    onPrimary = _root_ide_package_.com.kmpstudy.ui.theme.onPrimaryDark,
    primaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.primaryContainerDark,
    onPrimaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.onPrimaryContainerDark,

    secondary = _root_ide_package_.com.kmpstudy.ui.theme.secondaryDark,
    onSecondary = _root_ide_package_.com.kmpstudy.ui.theme.onSecondaryDark,
    secondaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.secondaryContainerDark,
    onSecondaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.onSecondaryContainerDark,

    tertiary = _root_ide_package_.com.kmpstudy.ui.theme.tertiaryDark,
    onTertiary = _root_ide_package_.com.kmpstudy.ui.theme.onTertiaryDark,
    tertiaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.tertiaryContainerDark,
    onTertiaryContainer = _root_ide_package_.com.kmpstudy.ui.theme.onTertiaryContainerDark,

    error = _root_ide_package_.com.kmpstudy.ui.theme.errorDark,
    onError = _root_ide_package_.com.kmpstudy.ui.theme.onErrorDark,
    errorContainer = _root_ide_package_.com.kmpstudy.ui.theme.errorContainerDark,
    onErrorContainer = _root_ide_package_.com.kmpstudy.ui.theme.onErrorContainerDark,

    background = _root_ide_package_.com.kmpstudy.ui.theme.backgroundDark,
    onBackground = _root_ide_package_.com.kmpstudy.ui.theme.onBackgroundDark,

    surface = _root_ide_package_.com.kmpstudy.ui.theme.surfaceDark,
    onSurface = _root_ide_package_.com.kmpstudy.ui.theme.onSurfaceDark,

    surfaceVariant = _root_ide_package_.com.kmpstudy.ui.theme.surfaceVariantDark,
    onSurfaceVariant = _root_ide_package_.com.kmpstudy.ui.theme.onSurfaceVariantDark,

    outline = _root_ide_package_.com.kmpstudy.ui.theme.outlineDark,
    outlineVariant = _root_ide_package_.com.kmpstudy.ui.theme.outlineVariantDark,

    scrim = _root_ide_package_.com.kmpstudy.ui.theme.scrimDark,

    inverseSurface = _root_ide_package_.com.kmpstudy.ui.theme.inverseSurfaceDark,
    inverseOnSurface = _root_ide_package_.com.kmpstudy.ui.theme.inverseOnSurfaceDark,
    inversePrimary = _root_ide_package_.com.kmpstudy.ui.theme.inversePrimaryDark,

    surfaceDim = _root_ide_package_.com.kmpstudy.ui.theme.surfaceDimDark,
    surfaceBright = _root_ide_package_.com.kmpstudy.ui.theme.surfaceBrightDark,

    surfaceContainerLowest = _root_ide_package_.com.kmpstudy.ui.theme.surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

// 可选：支持中/高对比度
val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    // 其他颜色同理...
)

val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    // 其他颜色同理...
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}

