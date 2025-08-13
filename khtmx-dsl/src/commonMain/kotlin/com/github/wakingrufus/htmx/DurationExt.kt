package com.github.wakingrufus.htmx

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

val OneSecond = 1.seconds
fun Duration.toHtmx(): String{
    return if(this < com.github.wakingrufus.htmx.OneSecond){
        "${this.inWholeMilliseconds}ms"
    } else {
        "${this.inWholeSeconds}s"
    }
}