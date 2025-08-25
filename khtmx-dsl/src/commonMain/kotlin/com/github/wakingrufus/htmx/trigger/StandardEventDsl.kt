package com.github.wakingrufus.htmx.trigger

import com.github.wakingrufus.htmx.AttributeModifier
import com.github.wakingrufus.htmx.modifier
import com.github.wakingrufus.htmx.toHtmx
import kotlin.time.Duration

@com.github.wakingrufus.htmx.HtmxDsl
class StandardEventDsl(val name: String) {
    private val modifiers: MutableList<AttributeModifier> = mutableListOf()

    @com.github.wakingrufus.htmx.HtmxDsl
    fun once() {
        modifiers.add(AttributeModifier("once"))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun changed() {
        modifiers.add(AttributeModifier("changed"))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun delay(duration: Duration) {
        modifiers.add(modifier("delay", duration.toHtmx()))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun throttle(duration: Duration) {
        modifiers.add(modifier("throttle", duration.toHtmx()))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun from(selector: String) {
        modifiers.add(modifier("from", selector))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun target(selector: String) {
        modifiers.add(modifier("target", selector))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun consume() {
        modifiers.add(modifier("consume"))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun queue(option: QueueOption) {
        modifiers.add(modifier("queue", option.stringValue))
    }

    operator fun invoke(): HxTriggerEvent = HxTriggerEvent.StandardEvent(name, modifiers)

    enum class QueueOption(val stringValue: String) {
        First("first"),
        Last("last"),
        All("all"),
        None("none")
    }
}
