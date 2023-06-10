package com.marcfearby.view.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcfearby.common.utils.PlayerIcons
import com.marcfearby.common.utils.getIcon

@Composable
fun PlayerButton(
    icon: PlayerIcons,
    description: String,
    modifier: Modifier = Modifier.padding(start = 0.dp, top = 15.dp, end = 0.dp, bottom = 15.dp),
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Image(
            painter = getIcon(icon),
            contentDescription = description
        )
    }
}