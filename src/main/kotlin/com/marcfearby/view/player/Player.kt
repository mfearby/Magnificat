package com.marcfearby.view.player

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcfearby.utils.PlayerIcons

@Composable
@Preview
fun Player() {
    Row {
        Text("Player goes here")

        PlayerButton(
            icon = PlayerIcons.Play,
            description = "Play",
            modifier = Modifier.padding(12.dp)
        ) {
            println("Play pressed")
        }

        PlayerButton(
            icon = PlayerIcons.Previous,
            description = "Previous"
        ) {
            println("Previous pressed")
        }


    }
}