package com.github.wakingrufus.htmx.target

import com.github.wakingrufus.htmx.HtmxDsl

@HtmxDsl
class SelectorDsl {
    private var target: String? = null

    @HtmxDsl
    fun `this`() {
        target = "this"
    }

    @HtmxDsl
    fun closest(selector: String) {
        target = "closest $selector"
    }

    @HtmxDsl
    fun find(selector: String) {
        target = "find $selector"
    }

    @HtmxDsl
    fun next(selector: String? = null) {
        target = if (selector != null) {
            "next $selector"
        } else {
            "next"
        }
    }

    @HtmxDsl
    fun previous(selector: String? = null) {
        target = if (selector != null) {
            "previous $selector"
        } else {
            "previous"
        }
    }

    operator fun invoke(): String? = target
}
