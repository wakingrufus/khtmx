package com.github.wakingrufus.htmx.swap

import com.github.wakingrufus.htmx.AttributeModifier

data class HxSwap(val type: HxSwapType?, val modifiers: List<AttributeModifier>) {
    operator fun invoke(): String = (listOf(type?.stringValue) + modifiers.map { it.value })
        .filterNotNull()
        .joinToString(" ")
}
