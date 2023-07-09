package com.marcfearby.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.marcfearby.audio.AudioPlayerState
import com.marcfearby.audio.IAudioPlayer
import com.marcfearby.controller.PlayerViewController
import com.marcfearby.controller.TabPaneController
import com.marcfearby.model.PlayerState.*
import org.koin.compose.koinInject

@Composable
@Preview
fun App(): () -> Unit {

    val audioPlayer = koinInject<IAudioPlayer>()

    var playerState by remember { mutableStateOf(Stopped) }
    var currentTrackIndex by remember { mutableStateOf(-1) }
    var currentTrackTitle by remember { mutableStateOf("") }
    var currentTrackProgress by remember { mutableStateOf(0f) }

    fun loadNewState(newState: AudioPlayerState) {
        playerState = newState.playerState
        currentTrackTitle = newState.currentTrackTitle
        currentTrackIndex = newState.currentTrackIndex
    }

    fun progressUpdater(percent: Float) {
        println("new progress received: $percent")
        currentTrackProgress = percent
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                PlayerViewController(
                    playerState = playerState,
                    togglePlayerState = {
                        loadNewState(
                            audioPlayer.togglePlayerState(state = it, updateProgress = ::progressUpdater)
                        )
                    },
                    currentTrackTitle = currentTrackTitle,
                    trackProgress = currentTrackProgress
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

    return {
        audioPlayer.release()
    }
}