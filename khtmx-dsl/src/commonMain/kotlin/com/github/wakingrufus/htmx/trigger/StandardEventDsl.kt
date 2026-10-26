package com.github.wakingrufus.htmx.trigger

import com.github.wakingrufus.htmx.AttributeModifier
import com.github.wakingrufus.htmx.HtmxDsl
import com.github.wakingrufus.htmx.modifier
import com.github.wakingrufus.htmx.toHtmx
import kotlin.time.Duration

@HtmxDsl
class StandardEventDsl(val name: String) {
    private val modifiers: MutableList<AttributeModifier> = mutableListOf()
    private var filter: String? = null

    fun filter(filter: String) {
        this.filter = filter
    }

    fun once() {
        modifiers.add(AttributeModifier("once"))
    }

    fun changed() {
        modifiers.add(AttributeModifier("changed"))
    }

    fun delay(duration: Duration) {
        modifiers.add(modifier("delay", duration.toHtmx()))
    }

    fun throttle(duration: Duration) {
        modifiers.add(modifier("throttle", duration.toHtmx()))
    }

    fun from(selector: String) {
        modifiers.add(modifier("from", selector))
    }

    fun target(selector: String) {
        modifiers.add(modifier("target", selector))
    }

    fun consume() {
        modifiers.add(modifier("consume"))
    }

    fun queue(option: QueueOption) {
        modifiers.add(modifier("queue", option.stringValue))
    }

    operator fun invoke(): HxTriggerEvent = HxTriggerEvent.StandardEvent(name, filter, modifiers)

    enum class QueueOption(val stringValue: String) {
        First("first"),
        Last("last"),
        All("all"),
        None("none")
    }
}
