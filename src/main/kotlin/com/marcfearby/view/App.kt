package com.marcfearby.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.marcfearby.view.player.PlayerController
import com.marcfearby.view.tab.FileTab

@Composable
@Preview
fun App() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                PlayerController()
            }
        }
    ) {
        Column {
            FileTab(
                horizontalSplitPercentage = 0.2f,
                onSplitterResize = {
                    println("Splitter: $it")
                }
            )
        }
    }
}