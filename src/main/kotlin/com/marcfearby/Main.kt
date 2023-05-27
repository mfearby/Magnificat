package com.marcfearby

import androidx.compose.ui.window.application
import com.marcfearby.view.App
import com.marcfearby.view.MainWindow

fun main() = application {
    MainWindow(
        onClose = ::exitApplication
    ) {
        App()
    }
}