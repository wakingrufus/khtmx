package com.github.wakingrufus.htmx.target

import com.github.wakingrufus.htmx.HtmxDsl

@HtmxDsl
class SelectorDsl {
    private var target: String? = null

    fun `this`() {
        target = "this"
    }

    fun closest(selector: String) {
        target = "closest $selector"
    }

    fun find(selector: String) {
        target = "find $selector"
    }

    fun next(selector: String? = null) {
        target = if (selector != null) {
            "next $selector"
        } else {
            "next"
        }
    }

    fun previous(selector: String? = null) {
        target = if (selector != null) {
            "previous $selector"
        } else {
            "previous"
        }
    }

    operator fun invoke(): String? = target
}
