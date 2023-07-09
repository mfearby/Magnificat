package com.marcfearby.controller

import androidx.compose.runtime.*
import com.marcfearby.model.PlayerState
import com.marcfearby.view.player.PlayerView

@Composable
fun PlayerViewController(
    playerState: PlayerState,
    togglePlayerState: (state: PlayerState) -> Unit,
    currentTrackTitle: String,
    trackProgress: Float
) {
    var isMuted by remember { mutableStateOf(false) }

    PlayerView(
        playerState = playerState,
        isMuted = isMuted,
        togglePlayerState = { togglePlayerState(it) },
        toggleMuted = { isMuted = it },
        currentTrackTitle = currentTrackTitle,
        trackProgress = trackProgress
    )
}