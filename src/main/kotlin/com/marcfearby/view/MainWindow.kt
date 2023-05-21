package com.marcfearby.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.prefs.Preferences

const val APPLICATION_NAME = "Magnificat"
const val WINDOW_WIDTH = "windowWidth"
const val WINDOW_HEIGHT = "windowHeight"
const val WINDOW_X = "windowX"
const val WINDOW_Y = "windowY"

@OptIn(FlowPreview::class)
@Composable
fun MainWindow(
    preferences: Preferences,
    onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    val width = preferences.getInt(WINDOW_WIDTH, 1100)
    val height = preferences.getInt(WINDOW_HEIGHT, 750)
    val winX = preferences.getInt(WINDOW_X, -1)
    val winY = preferences.getInt(WINDOW_Y, -1)

    val startPosition = if (winX < 0 && winY < 0)
        WindowPosition(Alignment.Center)
    else
        WindowPosition(winX.dp, winY.dp)

    val state = rememberWindowState(
        width = width.dp,
        height = height.dp,
        position = startPosition
    )

    Window(
        onCloseRequest = onClose,
        title = APPLICATION_NAME,
        state = state,
    ) {
        MaterialTheme {
            content()
        }

        LaunchedEffect(state) {
            snapshotFlow { state.size }
                .debounce(200)
                .onEach {
                    println("onWindowResize $it")
                    preferences.putInt(WINDOW_WIDTH, it.width.value.toInt())
                    preferences.putInt(WINDOW_HEIGHT, it.height.value.toInt())
                }
                .launchIn(this)

            snapshotFlow { state.position }
                .filter { it.isSpecified }
                .debounce(200)
                .onEach {
                    println("window moved to: $it")
                    preferences.putInt(WINDOW_X, it.x.value.toInt())
                    preferences.putInt(WINDOW_Y, it.y.value.toInt())
                }
                .launchIn(this)
        }
    }
}