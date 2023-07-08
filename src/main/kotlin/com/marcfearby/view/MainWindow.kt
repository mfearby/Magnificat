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
import com.marcfearby.common.settings.IMainWindowSettings
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.compose.koinInject

const val APPLICATION_NAME = "Magnificat"

@OptIn(FlowPreview::class)
@Composable
fun MainWindow(
    onClose: () -> Unit,
    content: @Composable () -> () -> Unit
) {
    val mainWindowSettings = koinInject<IMainWindowSettings>()
    val settings = mainWindowSettings.get()

    val startPosition = if (settings.x < 0 && settings.y < 0)
        WindowPosition(Alignment.Center)
    else
        WindowPosition(settings.x.dp, settings.y.dp)

    val state = rememberWindowState(
        width = settings.width.dp,
        height = settings.height.dp,
        position = startPosition
    )

    var cleanup: (() -> Unit)? = null

    Window(
        onCloseRequest = {
            cleanup?.invoke()
            onClose()
        },
        title = APPLICATION_NAME,
        state = state,
    ) {
        MaterialTheme {
            cleanup = content()
        }

        LaunchedEffect(state) {
            snapshotFlow { state.size }
                .debounce(200)
                .onEach {
                    println("onWindowResize $it")
                    mainWindowSettings.saveSize(it)
                }
                .launchIn(this)

            snapshotFlow { state.position }
                .filter { it.isSpecified }
                .debounce(200)
                .onEach {
                    println("window moved to: $it")
                    mainWindowSettings.savePosition(it)
                }
                .launchIn(this)
        }
    }
}