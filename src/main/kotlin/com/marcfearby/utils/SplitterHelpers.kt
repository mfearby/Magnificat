package com.marcfearby.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.SplitterScope
import java.awt.Cursor

enum class SplitterOrientation {
    Vertical,
    Horizontal
}

private fun Modifier.splitterCursor(orientation: SplitterOrientation): Modifier =
    pointerHoverIcon(
        PointerIcon(
            Cursor(
                if (orientation == SplitterOrientation.Vertical) {
                    Cursor.N_RESIZE_CURSOR
                } else {
                    Cursor.E_RESIZE_CURSOR
                }
            )
        )
    )

@OptIn(ExperimentalSplitPaneApi::class)
fun setupSplitter(scope: SplitterScope, orientation: SplitterOrientation) {
    with(scope) {
        visiblePart {
            Box(
                if (orientation == SplitterOrientation.Vertical) {
                    Modifier
                        .background(MaterialTheme.colors.background)
                        .height(1.dp)
                        .fillMaxWidth()
                } else {
                    Modifier
                        .background(MaterialTheme.colors.background)
                        .width(1.dp)
                        .fillMaxHeight()
                }
            )
        }
        handle {
            Box(
                if (orientation == SplitterOrientation.Vertical) {
                    Modifier
                        .testTag("verticalSplitter")
                        .markAsHandle()
                        .splitterCursor(SplitterOrientation.Vertical)
                        .background(SolidColor(Color.Gray), alpha = 0.50f)
                        .height(5.dp)
                        .fillMaxWidth()
                } else {
                    Modifier
                        .testTag("horizontalSplitter")
                        .markAsHandle()
                        .splitterCursor(SplitterOrientation.Horizontal)
                        .background(SolidColor(Color.Gray), alpha = 0.50f)
                        .width(5.dp)
                        .fillMaxHeight()
                }
            )
        }
    }
}