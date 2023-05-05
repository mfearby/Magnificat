package com.marcfearby.view.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.marcfearby.utils.SplitterOrientation
import com.marcfearby.utils.setupSplitter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.VerticalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

// Based on demo source:
// https://github.com/JetBrains/compose-multiplatform/blob/master/components/SplitPane/demo/src/jvmMain/kotlin/org/jetbrains/compose/splitpane/demo/Main.kt

@OptIn(ExperimentalSplitPaneApi::class, FlowPreview::class)
@Composable
fun FileTab(config: FileTabConfig) {
    val horizontalState = rememberSplitPaneState(config.horizontalSplitPercentage)
    val verticalState = rememberSplitPaneState(config.verticalSplitPercentage)
    val minSize = 75.dp

    HorizontalSplitPane(
        splitPaneState = horizontalState
    ) {
        first(minSize) {
            Box(Modifier.background(Color.Red).fillMaxSize())

            LaunchedEffect(horizontalState) {
                snapshotFlow { horizontalState.positionPercentage }
                    .debounce(200)
                    .onEach {
                        println("Splitter: $it")
                    }
                    .launchIn(this)
            }
        }

        second(minSize) {
            VerticalSplitPane(splitPaneState = verticalState) {
                first(minSize) {
                    Box(Modifier.background(Color.Blue).fillMaxSize())
                }
                second(minSize) {
                    Box(Modifier.background(Color.Green).fillMaxSize())
                }
                splitter {
                    setupSplitter(this, SplitterOrientation.Vertical)
                }
            }
        }

        splitter {
            setupSplitter(this, SplitterOrientation.Horizontal)
        }
    }
}

