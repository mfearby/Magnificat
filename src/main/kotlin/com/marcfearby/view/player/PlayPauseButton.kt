package com.marcfearby.view.player

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcfearby.common.utils.PlayerIcons.*
import com.marcfearby.model.PlayerState
import com.marcfearby.model.PlayerState.*

@Composable
fun PlayPauseButton(
    playerState: PlayerState,
    togglePlayerState: (state: PlayerState) -> Unit
) {
    val isPlaying = playerState == Playing

    PlayerButton(
        icon = if (isPlaying) Pause else Play,
        description = if (isPlaying) "Pause" else "Play",
        modifier = Modifier.padding(start = 0.dp, top = 12.dp, end = 0.dp, bottom = 12.dp)
    ) {
        togglePlayerState(
            if (isPlaying)
                Paused
            else
                Playing
        )
    }
}