package com.marcfearby.model

import kotlin.test.assertEquals
import org.junit.Test

internal class ProgressUpdateTest {

    @Test
    fun `calculates percentage progress`() {
        val progress = ProgressUpdate(65000, 13000)
        assertEquals(0.2f, progress.currentPositionPercentage)
    }

    @Test
    fun `calculates percentage if progress is complete`() {
        val progress = ProgressUpdate(65000, 65000)
        assertEquals(1f, progress.currentPositionPercentage)
    }

    @Test
    fun `returns 0 if totalLength is 0`() {
        val progress = ProgressUpdate(0, 13000)
        assertEquals(0f, progress.currentPositionPercentage)
    }

    @Test
    fun `returns 0 if currentPosition is 0`() {
        val progress = ProgressUpdate(65000, 0)
        assertEquals(0f, progress.currentPositionPercentage)
    }

    @Test
    fun `returns 0 if totalLength and currentPosition are 0`() {
        val progress = ProgressUpdate(0, 0)
        assertEquals(0f, progress.currentPositionPercentage)
    }
}