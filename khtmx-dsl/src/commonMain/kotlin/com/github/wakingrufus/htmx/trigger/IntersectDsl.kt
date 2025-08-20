package com.github.wakingrufus.htmx.trigger

class IntersectDsl {
    private val events: MutableSet<com.github.wakingrufus.htmx.AttributeModifier> = mutableSetOf()
    fun root(selector: String) {
        events.add(com.github.wakingrufus.htmx.AttributeModifier("root", selector))
    }

    fun threshold(amt: Double) {
        events.add(com.github.wakingrufus.htmx.AttributeModifier("threshold", amt.toString()))
    }

    operator fun invoke(): HxTriggerEvent.Intersect = HxTriggerEvent.Intersect(events)
}
