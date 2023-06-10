package com.marcfearby.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.marcfearby.controller.PlayerController
import com.marcfearby.controller.TabPaneController
import com.marcfearby.model.PlayerState

@Composable
@Preview
fun App() {

    var playerState by remember { mutableStateOf(PlayerState.Stopped) }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                PlayerController(
                    playerState = playerState,
                    togglePlayerState = { playerState = it }
                )
            }
        }
    ) {
        TabPaneController(
            playerState = playerState
        )
    }
}