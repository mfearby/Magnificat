package com.marcfearby

import androidx.compose.ui.window.application
import com.marcfearby.view.MainWindow
import java.util.prefs.Preferences

fun main() = application {
    val prefs: Preferences = Preferences.userNodeForPackage(this::class.java)

    MainWindow(prefs, onClose = {
        this.exitApplication()
    })
}