package eu.kanade.tachiyomi.ui.manga

import androidx.annotation.ColorInt
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.materialkolor.DynamicMaterialTheme
import eu.kanade.domain.ui.UiPreferences
import eu.kanade.presentation.components.AppBar
import eu.kanade.presentation.util.Screen
import tachiyomi.presentation.core.components.material.Button
import tachiyomi.presentation.core.components.material.ButtonDefaults
import tachiyomi.presentation.core.components.material.Scaffold
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

/**
 * A screen that displays a colors palette of current theme.
 */
class PaletteScreen(
    @ColorInt private val seedColor: Int?,
) : Screen() {

    // KMK -->
    private val uiPreferences = Injekt.get<UiPreferences>()
    private val themeCoverBased = uiPreferences.themeCoverBased().get()
    private val themeDarkAmoled = uiPreferences.themeDarkAmoled().get()
    private val themeCoverBasedStyle = uiPreferences.themeCoverBasedStyle().get()
    // KMK <--

    @Composable
    override fun Content() {
        val seedColor = seedColor?.let { Color(it) } ?: MaterialTheme.colorScheme.primary

        if (themeCoverBased) {
            DynamicMaterialTheme(
                seedColor = seedColor,
                useDarkTheme = isSystemInDarkTheme(),
                withAmoled = themeDarkAmoled,
                style = themeCoverBasedStyle,
                animate = true,
                content = { MaterialThemeContent(seedColor) },
            )
        } else {
            MaterialThemeContent(seedColor)
        }
    }

    @Composable
    fun MaterialThemeContent(seedColor: Color) {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = { scrollBehavior ->
                AppBar(
                    title = "Colors Palette",
                    navigateUp = navigator::pop,
                    scrollBehavior = scrollBehavior,
                )
            },
        ) { contentPadding ->
            Column(
                modifier = Modifier.padding(contentPadding),
            ) {
                ButtonsColor(
                    "seedColor & onPrimary",
                    seedColor,
                    "seedColor & contentColor",
                    seedColor,
                    MaterialTheme.colorScheme.onPrimary,
                )

                ButtonsColor(
                    "primary",
                    MaterialTheme.colorScheme.primary,
                    "primaryContainer",
                    MaterialTheme.colorScheme.primaryContainer
                )
                ButtonsColor(
                    "secondary",
                    MaterialTheme.colorScheme.secondary,
                    "secondaryContainer",
                    MaterialTheme.colorScheme.secondaryContainer
                )
                ButtonsColor(
                    "tertiary",
                    MaterialTheme.colorScheme.tertiary,
                    "tertiaryContainer",
                    MaterialTheme.colorScheme.tertiaryContainer
                )
                ButtonsColor(
                    "surface",
                    MaterialTheme.colorScheme.surface,
                    "surfaceVariant",
                    MaterialTheme.colorScheme.surfaceVariant
                )
                ButtonsColor(
                    "inverseSurface",
                    MaterialTheme.colorScheme.inverseSurface,
                    "surfaceTint",
                    MaterialTheme.colorScheme.surfaceTint
                )
                ButtonsColor(
                    "inversePrimary",
                    MaterialTheme.colorScheme.inversePrimary,
                    "background",
                    MaterialTheme.colorScheme.background
                )
                ButtonsColor(
                    "error",
                    MaterialTheme.colorScheme.error,
                    "errorContainer",
                    MaterialTheme.colorScheme.errorContainer
                )
                ButtonsColor(
                    "outline",
                    MaterialTheme.colorScheme.outline,
                    "outlineVariant",
                    MaterialTheme.colorScheme.outlineVariant
                )
                ButtonsColor(
                    "scrim",
                    MaterialTheme.colorScheme.scrim,
                    "surfaceBright",
                    MaterialTheme.colorScheme.surfaceBright
                )
                ButtonsColor(
                    "surfaceDim",
                    MaterialTheme.colorScheme.surfaceDim,
                    "surfaceContainer",
                    MaterialTheme.colorScheme.surfaceContainer
                )
                ButtonsColor(
                    "surfaceContainerHigh",
                    MaterialTheme.colorScheme.surfaceContainerHigh,
                    "surfaceContainerHighest",
                    MaterialTheme.colorScheme.surfaceContainerHighest
                )
                ButtonsColor(
                    "surfaceContainerLow",
                    MaterialTheme.colorScheme.surfaceContainerLow,
                    "surfaceContainerLowest",
                    MaterialTheme.colorScheme.surfaceContainerLowest
                )
            }
        }
    }
}

@Composable
fun ButtonsColor(
    name1: String,
    color1: Color,
    name2: String,
    color2: Color,
    contentColor1: Color = contentColorFor(backgroundColor = color1),
    contentColor2: Color = contentColorFor(backgroundColor = color2),
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Button(
            onClick = { },
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color1,
                contentColor = contentColor1,
            )
        ) {
            Text(
                text = name1,
            )
        }
        Button(
            onClick = { },
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color2,
                contentColor = contentColor2,
            ),
        ) {
            Text(
                text = name2
            )
        }
    }
}
