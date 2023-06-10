package com.marcfearby.controller

import androidx.compose.runtime.*
import com.marcfearby.view.player.PlayerView

@Composable
fun PlayerController(
//    playlistProvider: IPlaylistProvider
) {
    var isPlaying by remember { mutableStateOf(false) }
    var isMuted by remember { mutableStateOf(false) }

    PlayerView(
        isPlaying = isPlaying,
        isMuted = isMuted,
        togglePlaying = { isPlaying = it },
        toggleMuted = { isMuted = it },
        stopPlayback = { isPlaying = false }
    )
}