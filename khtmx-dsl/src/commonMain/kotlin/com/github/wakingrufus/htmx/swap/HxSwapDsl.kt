package com.github.wakingrufus.htmx.swap

import com.github.wakingrufus.htmx.HtmxDsl
import com.github.wakingrufus.htmx.toHtmx
import kotlin.time.Duration

@HtmxDsl
class HxSwapDsl(val type: HxSwapType) {
    private val modifiers = mutableListOf<com.github.wakingrufus.htmx.AttributeModifier>()

    @HtmxDsl
    fun transition() {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("transition"))
    }

    @HtmxDsl
    fun swap(duration: Duration) {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("swap", duration.toHtmx()))
    }

    @HtmxDsl
    fun settle(duration: Duration) {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("settle", duration.toHtmx()))
    }

    @HtmxDsl
    fun ignoreTitle(enabled: Boolean = true) {
        modifiers.add(com.github.wakingrufus.htmx.AttributeModifier("ignoreTitle", enabled.toString()))
    }

    operator fun invoke(): HxSwap {
        return HxSwap(type, modifiers)
    }
}
