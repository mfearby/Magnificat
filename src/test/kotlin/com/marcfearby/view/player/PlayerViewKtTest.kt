package com.marcfearby.view.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import com.marcfearby.model.PlayerState
import com.marcfearby.model.ProgressUpdate
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
internal class PlayerViewKtTest {

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
    private fun setupPlayer(
        currentTrack: String = "",
        trackProgress: ProgressUpdate = ProgressUpdate()
    ) = PlayerView(
        playerState = playerState,
        isMuted = isMuted,
        togglePlayerState = ::togglePlayerState,
        toggleMuted = ::toggleMuted,
        currentTrackTitle = currentTrack,
        trackProgress = trackProgress
    )

    @Test
    fun `toggle playerState to Playing when Play button is clicked`() = runDesktopComposeUiTest {
        setContent {
            setupPlayer()
        }
        onNodeWithContentDescription("Play").performClick()
        assertEquals(PlayerState.Playing, playerState)
    }

    @Test
    fun `toggle playerState to Paused when Pause button is clicked`() = runDesktopComposeUiTest {
        playerState = PlayerState.Playing
        setContent {
            setupPlayer()
        }
        onNodeWithContentDescription("Pause").performClick()
        assertEquals(PlayerState.Paused, playerState)
    }

    @Test
    fun `toggle playerState to Stopped when Stop button is clicked`() = runDesktopComposeUiTest {
        // TODO also clear currently-playing track name and reset slider to zero
        playerState = PlayerState.Playing
        setContent {
            setupPlayer()
        }
        onNodeWithContentDescription("Stop").performClick()
        assertEquals(PlayerState.Stopped, playerState)
    }

    @Test
    fun `should display the title of the currently playing track`() = runDesktopComposeUiTest {
        val trackName = "Hello there"
        setContent {
            setupPlayer(currentTrack = trackName)
        }
        onNodeWithTag("currentTrack").assertTextEquals(trackName)
    }

    @Test
    fun `should show a blank title if nothing is currently playing`() = runDesktopComposeUiTest {
        setContent {
            setupPlayer()
        }
        onNodeWithTag("currentTrack").assertTextEquals("")
    }

    @Test
    fun `should display progress update`() = runDesktopComposeUiTest {
        val progress = ProgressUpdate(69000, 5000)
        setContent {
            setupPlayer(trackProgress = progress)
        }
        onNodeWithTag("currentPosition").assertTextEquals("00:05")
        onNodeWithTag("totalLength").assertTextEquals("01:09")
    }
}