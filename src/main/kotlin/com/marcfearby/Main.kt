package com.marcfearby

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.marcfearby.view.App
import java.util.prefs.Preferences

const val WINDOW_WIDTH = "windowWidth"
const val WINDOW_HEIGHT = "windowHeight"
const val WINDOW_X = "windowX"
const val WINDOW_Y = "windowY"

fun main() = application {

    val prefs: Preferences = Preferences.userNodeForPackage(this::class.java)
    val width = prefs.getInt(WINDOW_WIDTH, 1100)
    val height = prefs.getInt(WINDOW_HEIGHT, 750)
    val winX = prefs.getInt(WINDOW_X, -1)
    val winY = prefs.getInt(WINDOW_Y, -1)

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
        onCloseRequest = ::exitApplication,
        title = "Magnificat",
        state = state,
    ) {
        App()

        LaunchedEffect(state) {
            snapshotFlow { state.size }
                .debounce(200)
                .onEach {
                    println("onWindowResize $it")
                    prefs.putInt(WINDOW_WIDTH, it.width.value.toInt())
                    prefs.putInt(WINDOW_HEIGHT, it.height.value.toInt())
                }
                .launchIn(this)

            snapshotFlow { state.position }
                .filter { it.isSpecified }
                .debounce(200)
                .onEach {
                    println("window moved to: $it")
                    prefs.putInt(WINDOW_X, it.x.value.toInt())
                    prefs.putInt(WINDOW_Y, it.y.value.toInt())
                }
                .launchIn(this)
        }
    }
}