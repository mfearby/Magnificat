package com.marcfearby.view.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.unit.dp
import org.junit.Test

internal class FileTabKtTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `return new percentage after resizing horizontal splitter`() = runDesktopComposeUiTest(400, 400) {
        // 0.2f = 250.0.dp, 0.3f = 337.0.dp
        var splitterPercentage = 0.2f

        setContent {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                FileTab(
                    horizontalSplitPercentage = splitterPercentage,
                    onSplitterResize = {
                        splitterPercentage = it
                        println("New splitter percentage: $it")
                    }
                )
            }
        }

        waitForIdle()

        // Move splitter to the right
        onNodeWithTag("horizontalSplitter")
            .performMouseInput {
                dragAndDrop(Offset(0.0f, 0.0f), Offset(100f, 0.0f))
            }

        waitForIdle()

        // TODO remove this from v1.5 of the test API when it should no longer be necessary
        Thread.sleep(200)

        assert(splitterPercentage > 0.5f)
        onNodeWithTag("fileTreePane").assertWidthIsAtLeast(220.dp)
    }
}