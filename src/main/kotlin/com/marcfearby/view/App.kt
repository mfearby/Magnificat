package com.marcfearby.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.marcfearby.controller.PlayerController
import com.marcfearby.controller.TabPaneController
import com.marcfearby.model.PlayerState.*

@Composable
@Preview
fun App() {

    var playerState by remember { mutableStateOf(Stopped) }
    val trackList = listOf("111", "222", "333", "444", "555")
    var currentTrackIndex by remember { mutableStateOf(-1) }
    var currentTrack by remember { mutableStateOf("") }

    fun stop() {
        playerState = Stopped
        currentTrackIndex = -1
        currentTrack = ""
    }

    fun play() {
        playerState = Playing
        if (currentTrackIndex < 0) {
            currentTrackIndex = 0
        }
        currentTrack = trackList[currentTrackIndex]
    }

    fun pause() {
        playerState = Paused
    }

    fun previous() {
        if (currentTrackIndex > 0) {
            playerState = Playing
            currentTrack = trackList[--currentTrackIndex]
            play()
        }
    }

    fun next() {
        if (currentTrackIndex < trackList.lastIndex) {
            playerState = Playing
            currentTrack = trackList[++currentTrackIndex]
            play()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                PlayerController(
                    playerState = playerState,
                    togglePlayerState = {
                        when (it) {
                            Playing -> play()
                            Paused -> pause()
                            Stopped -> stop()
                            MovingNext -> next()
                            MovingPrevious -> previous()
                        }
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