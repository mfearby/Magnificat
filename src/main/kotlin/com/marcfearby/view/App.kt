package com.marcfearby.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.marcfearby.audio.AudioPlayerState
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
    var currentTrackTitle by remember { mutableStateOf("") }
    val audioPlayer = koinInject<IAudioPlayer>()

    fun loadNewState(newState: AudioPlayerState) {
        playerState = newState.playerState
        currentTrackTitle = newState.currentTrackTitle
        currentTrackIndex = newState.currentTrackIndex
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                PlayerController(
                    playerState = playerState,
                    togglePlayerState = {
                        loadNewState(
                            audioPlayer.togglePlayerState(it)
                        )
                    },
                    currentTrackTitle = currentTrackTitle
                )
            }
        }
    ) {
        TabPaneController(
            playerState = playerState,
            onSetTrackList = { files, startIndex ->
                loadNewState(
                    audioPlayer.setTrackList(files, startIndex)
                )
            }
        )
    }
}