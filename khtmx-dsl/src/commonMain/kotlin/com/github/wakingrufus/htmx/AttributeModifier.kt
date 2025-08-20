package com.github.wakingrufus.htmx

class AttributeModifier(val name: String, val value: String? = null) {
    operator fun invoke(): String = if (value == null) {
        name
    } else {
        "$name:$value"
    }
}
