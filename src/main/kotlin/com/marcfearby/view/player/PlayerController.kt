package com.marcfearby.view.player

import androidx.compose.runtime.*

@Composable
fun PlayerController() {
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