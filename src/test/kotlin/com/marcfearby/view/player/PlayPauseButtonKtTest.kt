package com.marcfearby.view.player

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runDesktopComposeUiTest
import com.marcfearby.model.PlayerState
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class PlayPauseButtonKtTest {

    @Test
    fun `toggles playerState to Playing when Play button is clicked`() = runDesktopComposeUiTest {
        var playerState = PlayerState.Stopped
        setContent {
            PlayPauseButton(
                playerState = playerState,
                togglePlayerState = { playerState = it }
            )
        }
        onNodeWithContentDescription("Play").performClick()
        assertEquals(PlayerState.Playing, playerState)
    }

    @Test
    fun `toggles playerState to Paused when Pause button is clicked`() = runDesktopComposeUiTest {
        var playerState = PlayerState.Playing
        setContent {
            PlayPauseButton(
                playerState = playerState,
                togglePlayerState = { playerState = it }
            )
        }
        onNodeWithContentDescription("Pause").performClick()
        assertEquals(PlayerState.Paused, playerState)
    }

    @Test
    fun `shows Play button when playerState is Stopped`() = runDesktopComposeUiTest {
        val playerState = PlayerState.Stopped
        setContent {
            PlayPauseButton(
                playerState = playerState,
                togglePlayerState = { }
            )
        }
        onNodeWithContentDescription("Play").assertExists()
    }

}