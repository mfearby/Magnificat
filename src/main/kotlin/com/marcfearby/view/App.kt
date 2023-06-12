package com.marcfearby.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.marcfearby.audio.IAudioPlayer
import com.marcfearby.controller.PlayerController
import com.marcfearby.controller.TabPaneController
import com.marcfearby.model.PlayerState.*
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {

    var playerState by remember { mutableStateOf(Stopped) }
    var currentTrackIndex by remember { mutableStateOf(-1) }
    var currentTrack by remember { mutableStateOf("") }
    val audioPlayer = koinInject<IAudioPlayer>()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                PlayerController(
                    playerState = playerState,
                    togglePlayerState = {
                        val newState = audioPlayer.togglePlayerState(it)
                        playerState = newState.playerState
                        currentTrack = newState.currentTrack
                        currentTrackIndex = newState.currentTrackIndex
                    },
                    currentTrack = currentTrack
                )
            }
        }
    ) {
        TabPaneController(
            playerState = playerState
        )
    }
}