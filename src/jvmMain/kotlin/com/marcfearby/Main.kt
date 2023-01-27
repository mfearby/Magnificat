// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.marcfearby

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.marcfearby.view.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Magnificat",
        state = rememberWindowState(
            width = 1100.dp,
            height = 750.dp,
            position = WindowPosition(Alignment.Center)
        )
    ) {
        App()
    }
}
