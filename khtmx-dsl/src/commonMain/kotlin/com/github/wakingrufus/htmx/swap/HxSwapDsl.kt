package com.github.wakingrufus.htmx.swap

import com.github.wakingrufus.htmx.AttributeModifier
import com.github.wakingrufus.htmx.HtmxDsl
import com.github.wakingrufus.htmx.modifier
import com.github.wakingrufus.htmx.toHtmx
import kotlin.time.Duration

@HtmxDsl
class HxSwapDsl {
    /**
     * Listed separately on the possibly faulty assumption that it must be the first attribute
     */
    private var style: HxSwapType? = null
    private val modifiers = mutableListOf<AttributeModifier>()

    object Window

    @HtmxDsl
    val window: Window = Window

    object None

    @HtmxDsl
    val none: None = None

    @HtmxDsl
    val top: ScrollType = ScrollType.TOP

    @HtmxDsl
    val bottom: ScrollType = ScrollType.BOTTOM

    fun style(swapStyle: HxSwapType) {
        style = swapStyle
    }

    @HtmxDsl
    fun transition() {
        modifiers.add(modifier("transition"))
    }

    @HtmxDsl
    fun swap(duration: Duration) {
        modifiers.add(modifier("swap", duration.toHtmx()))
    }

    @HtmxDsl
    fun settle(duration: Duration) {
        modifiers.add(modifier("settle", duration.toHtmx()))
    }

    @HtmxDsl
    fun scroll(type: ScrollType) {
        modifiers.add(modifier("scroll", type.htmlString))
    }

    @HtmxDsl
    fun focusScroll(boolean: Boolean) {
        modifiers.add(modifier("focus-scroll", boolean.toString()))
    }

    @HtmxDsl
    fun show(type: ScrollType) {
        modifiers.add(modifier("show", type.htmlString))
    }

    @HtmxDsl
    fun show(none: None) {
        modifiers.add(modifier("show", "none"))
    }

    @HtmxDsl
    fun show(selector: String, type: ScrollType) {
        modifiers.add(modifier("show", selector, type.htmlString))
    }

    @HtmxDsl
    fun show(window: Window, type: ScrollType) {
        modifiers.add(modifier("show", "window", type.htmlString))
    }

    @HtmxDsl
    fun ignoreTitle(enabled: Boolean = true) {
        modifiers.add(modifier("ignoreTitle", enabled.toString()))
    }

    operator fun invoke(): HxSwap = HxSwap(style, modifiers)
}
