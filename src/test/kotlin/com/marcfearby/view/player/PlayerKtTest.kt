package com.marcfearby.view.player

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

internal class PlayerKtTest {

    @get:Rule
    val test = createComposeRule()

    @Test
    fun `toggle playing to true when clicking Play button`() {
        var playing = false

        test.setContent {
            Player(isPlaying = playing) {
                playing = it
            }
        }

        test.onNodeWithContentDescription("Play").performClick()
        assertEquals(true, playing)
    }

    @Test
    fun `toggle playing to false when clicking Pause button`() {
        var playing = true

        test.setContent {
            Player(isPlaying = playing) {
                playing = it
            }
        }

        test.onNodeWithContentDescription("Pause").performClick()
        assertEquals(false, playing)
    }
}