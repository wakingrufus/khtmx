package com.github.wakingrufus.htmx

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

val OneSecond = 1.seconds
fun Duration.toHtmx(): String = if (this < OneSecond) {
    "${this.inWholeMilliseconds}ms"
} else {
    "${this.inWholeSeconds}s"
}
