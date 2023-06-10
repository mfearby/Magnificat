package com.marcfearby.controller

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.marcfearby.model.PlayerState
import com.marcfearby.view.tab.FileTab
import java.util.*

@Composable
fun TabPaneController(
    playerState: PlayerState
) {
    var activeTab by remember { mutableStateOf(UUID.randomUUID()) }

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