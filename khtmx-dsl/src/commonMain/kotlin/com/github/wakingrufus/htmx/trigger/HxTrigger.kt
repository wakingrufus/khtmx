package com.github.wakingrufus.htmx.trigger

class HxTrigger(val events: Set<com.github.wakingrufus.htmx.trigger.HxTriggerEvent>) {
    operator fun invoke(): String {
        return events.joinToString(",") { it() }
    }
}
