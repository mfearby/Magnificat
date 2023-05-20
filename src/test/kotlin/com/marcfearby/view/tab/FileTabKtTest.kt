package com.marcfearby.view.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.marcfearby.utils.saveScreenShot
import org.junit.Rule
import org.junit.Test

internal class FileTabKtTest {

    @get:Rule
    val test = createComposeRule()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun moveSplitter() = runDesktopComposeUiTest(400, 400) {
        // 0.2f = 250.0.dp, 0.3f = 337.0.dp
        var splitterPercentage = 0.2f

        test.setContent {
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

        test.waitForIdle()
        saveScreenShot(this)

        // Try to move splitter to the right
        test.onNodeWithTag("horizontalSplitter")
            .performMouseInput {
                press()
                moveBy(delta = Offset(0.1f, 0.0f))
                release()
            }

        test.onNodeWithTag("fileTreePane").assertWidthIsAtLeast(330.dp)
        assert(splitterPercentage > 0.2f)
    }
}