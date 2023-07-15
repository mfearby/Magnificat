package com.marcfearby.model

import com.marcfearby.common.utils.Milliseconds

data class ProgressUpdate(
    var totalLength: Milliseconds = 0,
    var currentPosition: Milliseconds = 0
) {
    val currentPositionPercentage: Float
        get() = if (currentPosition > 0 && totalLength > 0)
            currentPosition / totalLength.toFloat()
        else
            0f
}