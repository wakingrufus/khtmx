package com.github.wakingrufus.htmx.swap

import com.github.wakingrufus.htmx.AttributeModifier

data class HxSwap(val type: HxSwapType, val modifiers: List<AttributeModifier>) {
    operator fun invoke(): String = if (modifiers.isEmpty()) {
        type.stringValue
    } else {
        "${type.stringValue} ${
            modifiers.joinToString(" ") { it() }
        }"
    }
}
