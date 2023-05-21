package com.marcfearby

import androidx.compose.ui.window.application
import com.marcfearby.view.App
import com.marcfearby.view.MainWindow
import java.util.prefs.Preferences

fun main() = application {
    MainWindow(
        preferences = Preferences.userNodeForPackage(this::class.java),
        onClose = ::exitApplication
    ) {
        App()
    }
}