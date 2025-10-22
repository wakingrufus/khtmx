package com.github.wakingrufus.htmx

import com.github.wakingrufus.htmx.swap.HxSwap
import com.github.wakingrufus.htmx.swap.HxSwapDsl
import com.github.wakingrufus.htmx.target.SelectorDsl
import com.github.wakingrufus.htmx.trigger.HxTrigger
import com.github.wakingrufus.htmx.trigger.HxTriggerDsl
import kotlinx.html.HTMLTag

@HtmxDsl
class HxDsl(val verb: HttpVerb, val path: String) {
    private var swap: HxSwap? = null
    private var trigger: HxTrigger? = null
    private var hxParams: String? = null
    private var hxPushUrl: String? = null
    private var hxTarget: String? = null
    private var hxVals: String? = null

    @HtmxDsl
    fun hxTarget(selector: String) {
        hxTarget = selector
    }

    @HtmxDsl
    fun hxTarget(selector: SelectorDsl.() -> Unit) {
        hxTarget = SelectorDsl().apply(selector).invoke()
    }

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
    var hxSelectOob: String? = null

    @HtmxDsl
    fun hxSwap(dsl: HxSwapDsl.() -> Unit = {}) {
        swap = HxSwapDsl().apply(dsl)()
    }

    @HtmxDsl
    fun hxTrigger(dsl: HxTriggerDsl.() -> Unit = {}) {
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

    @HtmxDsl
    fun hxVals(json: String) {
        hxVals = json
    }

    operator fun invoke(element: HTMLTag) {
        element.apply {
            attributes["hx-${verb.attrName}"] = path
            swap?.let { this.hxSwap(it()) }
            trigger?.let { this.hxTrigger(it()) }
            hxTarget?.let { this.hxTarget(it) }
            hxPushUrl?.let { this.hxPushUrl(it) }
            hxSelect?.let { hxSelect(it) }
            hxSelectOob?.let { hxSelectOob(it) }
            hxParams?.let { hxParams(it) }
            hxVals?.let { hxVals(it) }
        }
    }
}
