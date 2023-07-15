package com.marcfearby.common.utils

import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration

typealias Milliseconds = Long

fun Milliseconds.toLabel(): String {
    return this
        .toDuration(DurationUnit.MILLISECONDS)
        .toComponents { _, minutes, seconds, _ ->
            "%02d:%02d".format(minutes, seconds)
        }
}

fun isMacOS(): Boolean {
    val os = System.getProperty("os.name", "generic").lowercase(Locale.ENGLISH)
    return os.indexOf("mac") >= 0 || os.indexOf("darwin") >= 0
}