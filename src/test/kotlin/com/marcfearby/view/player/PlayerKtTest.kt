package com.marcfearby.view.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.After
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class PlayerKtTest {

    @get:Rule
    val test = createComposeRule()

    private var isPlaying = false
    private var isMuted = false

    private fun togglePlaying(playing: Boolean) {
        isPlaying = playing
    }

    private fun toggleMuted(muted: Boolean) {
        isMuted = muted
    }

    private fun stopPlayback() {
        isPlaying = false
    }

    @After
    fun afterEach() {
        isPlaying = false
        isMuted = false
    }

    @Composable
    private fun setupPlayer() = Player(
        isPlaying = isPlaying,
        isMuted = isMuted,
        togglePlaying = ::togglePlaying,
        toggleMuted = ::toggleMuted,
        stopPlayback = ::stopPlayback
    )

    @Test
    fun `toggle isPlaying to true when Play button is clicked`() {
        test.setContent {
            setupPlayer()
        }

        test.onNodeWithContentDescription("Play").performClick()
        assertEquals(true, isPlaying)
    }

    @Test
    fun `toggle isPlaying to false when Pause button is clicked`() {
        isPlaying = true
        test.setContent {
            setupPlayer()
        }

        test.onNodeWithContentDescription("Pause").performClick()
        assertEquals(false, isPlaying)
    }

    @Test
    fun `toggle isPlaying to false when Stop button is clicked`() {
        // TODO also clear currently-playing track name and reset slider to zero
        isPlaying = true
        test.setContent {
            setupPlayer()
        }
        test.onNodeWithContentDescription("Stop").performClick()
        assertEquals(false, isPlaying)
    }
}