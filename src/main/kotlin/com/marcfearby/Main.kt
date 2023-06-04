package com.marcfearby

import androidx.compose.ui.window.application
import com.marcfearby.common.di.initKoin
import com.marcfearby.view.App
import com.marcfearby.view.MainWindow

fun main() {
    initKoin()
    application {
        MainWindow(
            onClose = ::exitApplication
        ) {
            App()
        }
    }
}