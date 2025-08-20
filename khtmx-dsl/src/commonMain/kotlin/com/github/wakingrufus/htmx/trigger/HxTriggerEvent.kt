package com.github.wakingrufus.htmx.trigger

import com.github.wakingrufus.htmx.AttributeModifier
import com.github.wakingrufus.htmx.toHtmx
import kotlin.time.Duration

sealed class HxTriggerEvent(open val name: String) {
    abstract operator fun invoke(): String
    class Poll(val interval: Duration) : HxTriggerEvent("every") {
        override fun invoke(): String = "every " + interval.toHtmx()
    }

    class StandardEvent(override val name: String, private val modifiers: List<AttributeModifier> = emptyList()) :
        HxTriggerEvent(name) {
        override fun invoke(): String = "$name " + modifiers.joinToString(" ") { it() }
    }

    object Load : HxTriggerEvent("load") {
        override fun invoke(): String = name
    }

    object Revealed : HxTriggerEvent("revealed") {
        override fun invoke(): String = name
    }

    class Intersect(val modifiers: Set<AttributeModifier> = emptySet()) : HxTriggerEvent("intersect") {
        override fun invoke(): String = "$name " + modifiers.joinToString(" ") { it() }
    }
}
