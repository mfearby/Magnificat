package com.marcfearby.common.utils

import kotlin.test.assertEquals
import org.junit.Test

internal class CommonTest {

    @Test
    fun `returns zero minutes and seconds if milliseconds is zero`() {
        val milliseconds: Milliseconds = 0
        assertEquals("00:00", milliseconds.toLabel())
    }

    @Test
    fun `converts milliseconds to minutes and seconds format`() {
        val milliseconds: Milliseconds = 69000
        assertEquals("01:09", milliseconds.toLabel())
    }
}