package com.marcfearby.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.marcfearby.view.player.Player
import com.marcfearby.view.tab.FileTab

@Composable
@Preview
fun App() {
    var isPlaying by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Player(isPlaying = isPlaying) {
                    isPlaying = it
                }
            }
        }
    ) {
        Column {
            Text("isPlaying: $isPlaying")

            FileTab(
                horizontalSplitPercentage = 0.2f,
                onSplitterResize = {
                    println("Splitter: $it")
                }
            )
        }
    }
}