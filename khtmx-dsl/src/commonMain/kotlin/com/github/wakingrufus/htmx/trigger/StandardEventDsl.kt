package com.github.wakingrufus.htmx.trigger

import com.github.wakingrufus.htmx.toHtmx
import kotlin.time.Duration

@com.github.wakingrufus.htmx.HtmxDsl
class StandardEventDsl(val name: String) {
    private val modifiers: MutableList<com.github.wakingrufus.htmx.AttributeModifier> = mutableListOf()

    @com.github.wakingrufus.htmx.HtmxDsl
    fun once() {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("once"))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun changed() {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("changed"))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun delay(duration: Duration) {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("delay", duration.toHtmx()))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun throttle(duration: Duration) {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("throttle", duration.toHtmx()))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun from(selector: String) {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("from", selector))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun target(selector: String) {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("target", selector))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun consume() {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("consume"))
    }

    @com.github.wakingrufus.htmx.HtmxDsl
    fun queue(option: QueueOption) {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("queue", option.stringValue))
    }

    operator fun invoke(): com.github.wakingrufus.htmx.trigger.HxTriggerEvent {
        return com.github.wakingrufus.htmx.trigger.HxTriggerEvent.StandardEvent(name, modifiers)
    }

    enum class QueueOption(val stringValue: String) {
        First("first"), Last("last"), All("all"), None("none")
    }
}