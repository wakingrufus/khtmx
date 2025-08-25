package com.github.wakingrufus.htmx

import kotlin.jvm.JvmInline

@JvmInline
value class AttributeModifier(val value: String)

fun modifier(vararg parts: String): AttributeModifier = AttributeModifier(parts.joinToString(":"))
