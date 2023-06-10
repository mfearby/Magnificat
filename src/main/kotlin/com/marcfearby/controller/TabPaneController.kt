package com.marcfearby.controller

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.marcfearby.model.PlayerState
import com.marcfearby.view.tab.FileTab

@Composable
fun TabPaneController(
    playerState: PlayerState
) {
    var activeTabIndex by remember { mutableStateOf(-1) }

    Column {
        Text("Player State: $playerState")

        FileTab(
            horizontalSplitPercentage = 0.2f,
            onSplitterResize = {
                println("Splitter: $it")
            }
        )
    }
}