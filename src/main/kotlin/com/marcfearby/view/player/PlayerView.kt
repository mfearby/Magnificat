package com.marcfearby.view.player

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcfearby.common.utils.PlayerIcons
import com.marcfearby.model.PlayerState
import com.marcfearby.model.PlayerState.*

@Composable
@Preview
fun PlayerView(
    playerState: PlayerState,
    isMuted: Boolean,
    togglePlayerState: (state: PlayerState) -> Unit,
    toggleMuted: (muted: Boolean) -> Unit,
    currentTrackTitle: String
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
            togglePlayerState(MovingPrevious)
            println("Previous pressed")
        }

        PlayerButton(
            icon = PlayerIcons.Stop,
            description = "Stop"
        ) {
            togglePlayerState(Stopped)
            println("Stop pressed")
        }

        PlayerButton(
            icon = PlayerIcons.Next,
            description = "Next"
        ) {
            togglePlayerState(MovingNext)
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
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp)
        ) {
            Text(
                text = currentTrackTitle,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().testTag("currentTrack")
                    .padding(start = 0.dp, top = 5.dp, end = 0.dp, bottom = 0.dp)
            )
            var sliderState by remember { mutableStateOf(0f) }
            Slider(
                value = sliderState,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onValueChange = { progress ->
                    println("slider state: $progress")
                    sliderState = progress
                }
            )
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