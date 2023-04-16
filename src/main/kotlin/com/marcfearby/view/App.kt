package com.marcfearby.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.marcfearby.view.player.Player

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Player()
            }
        }
    ) {
        Button(onClick = {
            text = "Hello, Magnificat!"
        }) {
            Text(text)
        }
    }
}