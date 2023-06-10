package com.marcfearby.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

enum class PlayerIcons(val fileName: String) {
    Next("player_skip_next"),
    Pause("player_pause"),
    Play("player_play_arrow"),
    Previous("player_skip_previous"),
    Stop("player_stop"),
    Muted("player_volume_off"),
    Unmuted("player_volume_up"),
}

@Composable
fun getIcon(icon: PlayerIcons): Painter {
    return painterResource("icons/${icon.fileName}.svg")
}