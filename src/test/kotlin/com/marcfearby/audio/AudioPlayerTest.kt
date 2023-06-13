package com.marcfearby.audio

import com.marcfearby.model.PlayerState
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

class AudioPlayerTest: KoinTest {

    @Before
    fun setUp() {
        startKoin {
            modules(audioPlayerModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    private val audioPlayer : IAudioPlayer by inject()

    @Test
    fun `plays the first track`() {
        val expected = AudioPlayerState(0, "111", PlayerState.Playing)
        val actual = audioPlayer.play()
        assertEquals(expected, actual)
    }
}