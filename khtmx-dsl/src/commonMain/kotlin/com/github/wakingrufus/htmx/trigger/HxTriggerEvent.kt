package com.github.wakingrufus.htmx.trigger

import com.github.wakingrufus.htmx.toHtmx
import kotlin.time.Duration

sealed class HxTriggerEvent(open val name: String) {
    abstract operator fun invoke(): String
    class Poll(val interval: Duration) : com.github.wakingrufus.htmx.trigger.HxTriggerEvent("every") {
        override fun invoke(): String = "every " + interval.toHtmx()
    }

    class StandardEvent(override val name: String, val modifiers: List<com.github.wakingrufus.htmx.AttributeModifier> = emptyList()) :
        com.github.wakingrufus.htmx.trigger.HxTriggerEvent(name) {
        override fun invoke(): String = "$name " + modifiers.joinToString(" ") { it() }
    }

    object Load : com.github.wakingrufus.htmx.trigger.HxTriggerEvent("load") {
        override fun invoke(): String = name
    }

    object Revealed : com.github.wakingrufus.htmx.trigger.HxTriggerEvent("revealed") {
        override fun invoke(): String = name
    }

    class Intersect(val modifiers: Set<com.github.wakingrufus.htmx.AttributeModifier> = emptySet()) : com.github.wakingrufus.htmx.trigger.HxTriggerEvent("intersect") {
        override fun invoke(): String = "$name " + modifiers.joinToString(" ") { it() }
    }
}
