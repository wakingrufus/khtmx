package com.github.wakingrufus.htmx.trigger

class HxTrigger(val events: Set<HxTriggerEvent>) {
    operator fun invoke(): String = events.joinToString(", ") { it() }
}
