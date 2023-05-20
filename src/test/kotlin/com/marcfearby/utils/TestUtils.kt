package com.marcfearby.utils

import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.test.DesktopComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.pathString
import kotlin.io.path.writeBytes

@OptIn(ExperimentalTestApi::class)
fun saveScreenShot(testInstance: DesktopComposeUiTest): Path {
    val img: Image = Image.makeFromBitmap(testInstance.captureToImage().asSkiaBitmap())
    val pngFile = Files.createTempFile("test-screenshot", ".png")
    val encodedImage = img.encodeToData(EncodedImageFormat.PNG) ?: error("Could not encode image to PNG")
    pngFile.writeBytes(encodedImage.bytes)

    // File is actually a transparent PNG - no visible content :-\
    // Path will be like this: /var/folders/_9/8rc1bdf93fvdqkhbpp0kr_ch0000gq/T/test-screenshot4879348737488007814.png
    println("Saved screenshot: ${pngFile.pathString}")

    return pngFile
}