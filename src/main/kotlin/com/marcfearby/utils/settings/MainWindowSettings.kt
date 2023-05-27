package com.marcfearby.utils.settings

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.WindowPosition
import java.util.prefs.Preferences

const val WINDOW_WIDTH = "windowWidth"
const val WINDOW_HEIGHT = "windowHeight"
const val WINDOW_X = "windowX"
const val WINDOW_Y = "windowY"

data class MainWindow(
    var width: Int,
    var height: Int,
    var x: Int,
    var y: Int
)

interface IMainWindowSettings {
    fun get(): MainWindow
    fun savePosition(position: WindowPosition)
    fun saveSize(size: DpSize)
}

object MainWindowSettings: IMainWindowSettings {
    private val prefs: Preferences = Preferences.userNodeForPackage(this::class.java)

    override fun get(): MainWindow {
        val width = prefs.getInt(WINDOW_WIDTH, 1100)
        val height = prefs.getInt(WINDOW_HEIGHT, 750)
        val winX = prefs.getInt(WINDOW_X, -1)
        val winY = prefs.getInt(WINDOW_Y, -1)

        return MainWindow(width, height, winX, winY)
    }

    override fun savePosition(position: WindowPosition) {
        prefs.putInt(WINDOW_X, position.x.value.toInt())
        prefs.putInt(WINDOW_Y, position.y.value.toInt())
    }

    override fun saveSize(size: DpSize) {
        prefs.putInt(WINDOW_WIDTH, size.width.value.toInt())
        prefs.putInt(WINDOW_HEIGHT, size.height.value.toInt())
    }
}