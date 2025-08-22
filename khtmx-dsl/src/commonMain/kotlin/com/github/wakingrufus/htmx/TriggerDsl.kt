package com.github.wakingrufus.htmx

import com.github.wakingrufus.htmx.swap.HxSwap
import com.github.wakingrufus.htmx.swap.HxSwapDsl
import com.github.wakingrufus.htmx.swap.HxSwapType
import com.github.wakingrufus.htmx.trigger.HxTrigger
import com.github.wakingrufus.htmx.trigger.HxTriggerDsl
import kotlinx.html.HTMLTag

@HtmxDsl
class TriggerDsl(val verb: HttpVerb, val path: String) {
    private var swap: HxSwap? = null
    private var trigger: HxTrigger? = null
    private var hxParams: String? = null
    private var hxPushUrl: String? = null

    @HtmxDsl
    var target: String? = null

    @HtmxDsl
    fun hxPushUrl(customUrl: String) {
        hxPushUrl = customUrl
    }

    @HtmxDsl
    fun hxPushUrl(enabled: Boolean) {
        hxPushUrl = enabled.toString()
    }

    @HtmxDsl
    var hxSelect: String? = null

    @HtmxDsl
    fun swap(type: HxSwapType, dsl: HxSwapDsl.() -> Unit = {}) {
        swap = HxSwapDsl(type).apply(dsl)()
    }

    @HtmxDsl
    fun trigger(dsl: HxTriggerDsl.() -> Unit = {}) {
        trigger = HxTriggerDsl().apply(dsl)()
    }

    @HtmxDsl
    fun allParams() {
        hxParams = "*"
    }

    @HtmxDsl
    fun noParams() {
        hxParams = "none"
    }

    @HtmxDsl
    fun excludeParams(vararg params: String) {
        hxParams = "not " + params.joinToString(",")
    }

    @HtmxDsl
    fun includeParams(vararg params: String) {
        hxParams = params.joinToString(",")
    }

    operator fun invoke(element: HTMLTag) {
        element.apply {
            attributes["hx-${verb.attrName}"] = path
            swap?.let { hxSwap(it()) }
            trigger?.let { attributes["hx-trigger"] = it() }
            target?.let { hxTarget(it) }
            hxPushUrl?.let { this.hxPushUrl(it) }
            hxSelect?.let { hxSelect(it) }
            hxParams?.let { hxParams(it) }
        }
    }
}
