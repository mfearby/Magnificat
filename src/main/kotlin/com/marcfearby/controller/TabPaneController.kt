package com.marcfearby.controller

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.marcfearby.model.PlayerState
import com.marcfearby.view.tab.FileTab
import java.util.*

@Composable
fun TabPaneController(
    playerState: PlayerState,
    onSetTrackList: (files: List<String>, startIndex: Int) -> Unit
) {
    var activeTab by remember { mutableStateOf(UUID.randomUUID()) }

    Column {
        Text("Player State: $playerState")

        Button(
            onClick = {
                onSetTrackList(listOf("666", "777", "888", "999"), 0)
            }
        ) {
            Text("load playlist test")
        }

        FileTab(
            horizontalSplitPercentage = 0.2f,
            onSplitterResize = {
                println("Splitter: $it")
            }
        )
    }
}