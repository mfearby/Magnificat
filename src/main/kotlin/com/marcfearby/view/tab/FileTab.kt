package com.marcfearby.view.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.marcfearby.utils.SplitterOrientation
import com.marcfearby.utils.setupSplitter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

// Based on demo source:
// https://github.com/JetBrains/compose-multiplatform/blob/master/components/SplitPane/demo/src/jvmMain/kotlin/org/jetbrains/compose/splitpane/demo/Main.kt

@OptIn(ExperimentalSplitPaneApi::class, FlowPreview::class)
@Composable
fun FileTab(
    horizontalSplitPercentage: Float,
    onSplitterResize: (positionPercentage: Float) -> Unit
) {
    val horizontalState = rememberSplitPaneState(horizontalSplitPercentage)
    val minSize = 75.dp

    HorizontalSplitPane(
        splitPaneState = horizontalState
    ) {
        first(minSize) {
            Box(
                Modifier
                    .testTag("fileTreePane")
                    .background(Color.Red)
                    .fillMaxSize()
            )

            LaunchedEffect(horizontalState) {
                snapshotFlow { horizontalState.positionPercentage }
                    .debounce(200)
                    .onEach { onSplitterResize(it) }
                    .launchIn(this)
            }
        }

        second(minSize) {
            Text("Files grid here")
        }

        splitter {
            setupSplitter(this, SplitterOrientation.Horizontal)
        }
    }
}

