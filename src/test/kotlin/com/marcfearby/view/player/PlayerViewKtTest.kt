package com.marcfearby.view.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.marcfearby.model.PlayerState
import org.junit.After
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class PlayerViewKtTest {

    @get:Rule
    val test = createComposeRule()

    private var playerState = PlayerState.Stopped
    private var isMuted = false

    private fun togglePlayerState(state: PlayerState) {
        playerState = state
    }

    private fun toggleMuted(muted: Boolean) {
        isMuted = muted
    }

    @After
    fun afterEach() {
        playerState = PlayerState.Stopped
        isMuted = false
    }

    @Composable
    private fun setupPlayer() = PlayerView(
        playerState = playerState,
        isMuted = isMuted,
        togglePlayerState = ::togglePlayerState,
        toggleMuted = ::toggleMuted,
        currentTrack = ""
    )

    @Test
    fun `toggle playerState to Playing when Play button is clicked`() {
        test.setContent {
            setupPlayer()
        }
        test.onNodeWithContentDescription("Play").performClick()
        assertEquals(PlayerState.Playing, playerState)
    }

    @Test
    fun `toggle playerState to Paused when Pause button is clicked`() {
        playerState = PlayerState.Playing
        test.setContent {
            setupPlayer()
        }
        test.onNodeWithContentDescription("Pause").performClick()
        assertEquals(PlayerState.Paused, playerState)
    }

    @Test
    fun `toggle playerState to Stopped when Stop button is clicked`() {
        // TODO also clear currently-playing track name and reset slider to zero
        playerState = PlayerState.Playing
        test.setContent {
            setupPlayer()
        }
        test.onNodeWithContentDescription("Stop").performClick()
        assertEquals(PlayerState.Stopped, playerState)
    }
}