package com.marcfearby.view.player

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcfearby.common.utils.PlayerIcons
import com.marcfearby.model.PlayerState

@Composable
@Preview
fun PlayerView(
    playerState: PlayerState,
    isMuted: Boolean,
    togglePlayerState: (state: PlayerState) -> Unit,
    toggleMuted: (muted: Boolean) -> Unit
) {
    Row {
        PlayPauseButton(
            playerState = playerState,
            togglePlayerState = { togglePlayerState(it) }
        )

        PlayerButton(
            icon = PlayerIcons.Previous,
            description = "Previous"
        ) {
            println("Previous pressed")
        }

        PlayerButton(
            icon = PlayerIcons.Stop,
            description = "Stop"
        ) {
            togglePlayerState(PlayerState.Stopped)
            println("Stop pressed")
        }

        PlayerButton(
            icon = PlayerIcons.Next,
            description = "Next"
        ) {
            println("Next pressed")
        }

        Text(
            text = "00:00",
            fontSize = 14.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 15.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        )

        Column(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp)
        ) {
            Text("title goes here")
            Text("progress slider goes here")
        }

        Text(
            text = "-00:00",
            fontSize = 14.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 15.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        )

        PlayerButton(
            icon = if (isMuted) PlayerIcons.Muted else PlayerIcons.Unmuted,
            description = if (isMuted) "Muted" else "Unmuted",
        ) {
            toggleMuted(!isMuted)
            println("Muted/Unmuted pressed")
        }

        Text(
            text = "vol slider",
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 15.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        )
    }
}