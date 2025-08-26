package com.github.wakingrufus.htmx.trigger

import com.github.wakingrufus.htmx.modifier

class IntersectDsl {
    private val events: MutableSet<com.github.wakingrufus.htmx.AttributeModifier> = mutableSetOf()
    fun root(selector: String) {
        events.add(modifier("root", selector))
    }

    fun threshold(amt: Double) {
        events.add(modifier("threshold", amt.toString()))
    }

    operator fun invoke(): HxTriggerEvent.Intersect = HxTriggerEvent.Intersect(events)
}
