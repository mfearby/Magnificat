package com.marcfearby.view.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marcfearby.utils.PlayerIcons
import com.marcfearby.utils.getIcon

@Composable
fun PlayerButton(
    icon: PlayerIcons,
    description: String,
    modifier: Modifier = Modifier.padding(15.dp),
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Image(
            painter = getIcon(icon),
            contentDescription = description,
            modifier = modifier
        )
    }
}