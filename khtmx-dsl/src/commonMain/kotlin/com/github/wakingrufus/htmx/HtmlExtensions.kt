package com.github.wakingrufus.htmx

import com.github.wakingrufus.htmx.swap.HxSwapDsl
import com.github.wakingrufus.htmx.swap.HxSwapType
import kotlinx.html.HTMLTag

/**
 * Entry point for the DSL which works with any [HttpVerb].
 * @param dsl high-level DSL that helps to author triggers in a way that coordinates the related attributes and enforce type safety
 */
@HtmxDsl
fun HTMLTag.hx(verb: HttpVerb, path: String, dsl: HxDsl.() -> Unit = {}) {
    HxDsl(verb, path).apply(dsl).invoke(this)
}

/**
 * Convenience method for using [hx] for a POST
 * https://htmx.org/attributes/hx-post/
 * @param dsl high-level DSL that helps to author triggers in a way that coordinates the related attributes and enforce type safety
 */
@HtmxDsl
fun HTMLTag.hxPost(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.POST, path, dsl)
}

/**
 * Convenience method for using [hx] for a GET
 * https://htmx.org/attributes/hx-get/
 * @param dsl high-level DSL that helps to author triggers in a way that coordinates the related attributes and enforce type safety
 */
@HtmxDsl
fun HTMLTag.hxGet(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.GET, path, dsl)
}

/**
 * Convenience method for using [hx] for a PUT
 * https://htmx.org/attributes/hx-put/
 * @param dsl high-level DSL that helps to author triggers in a way that coordinates the related attributes and enforce type safety
 */
@HtmxDsl
fun HTMLTag.hxPut(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.PUT, path, dsl)
}

/**
 * Convenience method for using [hx] for a PATCH
 * https://htmx.org/attributes/hx-patch/
 * @param dsl high-level DSL that helps to author triggers in a way that coordinates the related attributes and enforce type safety
 */
@HtmxDsl
fun HTMLTag.hxPatch(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.PATCH, path, dsl)
}

/**
 * Convenience method for using [hx] for a DELETE
 * https://htmx.org/attributes/hx-delete/
 * @param dsl high-level DSL that helps to author triggers in a way that coordinates the related attributes and enforce type safety
 */
@HtmxDsl
fun HTMLTag.hxDelete(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.DELETE, path, dsl)
}

/**
 * https://htmx.org/attributes/hx-boost/
 */
@HtmxDsl
fun HTMLTag.hxBoost(boost: Boolean) {
    attributes["hx-boost"] = boost.toString()
}

/**
 * https://htmx.org/attributes/hx-select/
 */
@HtmxDsl
fun HTMLTag.hxSelect(selector: String) {
    attributes["hx-select"] = selector
}

/**
 * https://htmx.org/attributes/hx-select-oob/
 */
@HtmxDsl
fun HTMLTag.hxSelectOob(selector: String) {
    attributes["hx-select-oob"] = selector
}

/**
 * https://htmx.org/attributes/hx-params/
 */
@HtmxDsl
fun HTMLTag.hxParams(params: String) {
    attributes["hx-params"] = params
}

/**
 * https://htmx.org/attributes/hx-push-url/
 */
@HtmxDsl
fun HTMLTag.hxPushUrl(value: Boolean) {
    attributes["hx-push-url"] = value.toString()
}

/**
 * https://htmx.org/attributes/hx-push-url/
 */
@HtmxDsl
fun HTMLTag.hxPushUrl(value: String) {
    attributes["hx-push-url"] = value
}

/**
 * https://htmx.org/attributes/hx-swap/
 */
@HtmxDsl
fun HTMLTag.hxSwap(value: String) {
    attributes["hx-swap"] = value
}

/**
 * https://htmx.org/attributes/hx-swap/
 */
@HtmxDsl
fun HTMLTag.hxSwap(dsl: HxSwapDsl.() -> Unit = {}) {
    attributes["hx-swap"] = HxSwapDsl().apply(dsl).invoke().invoke()
}

/**
 * https://htmx.org/attributes/hx-select-oob/
 */
@HtmxDsl
fun HTMLTag.hxSwapOob(value: Boolean) {
    attributes["hx-swap-oob"] = value.toString()
}

/**
 * https://htmx.org/attributes/hx-select-oob/
 */
@HtmxDsl
fun HTMLTag.hxSwapOob(type: HxSwapType) {
    attributes["hx-swap-oob"] = type.stringValue
}

/**
 * https://htmx.org/attributes/hx-select-oob/
 */
@HtmxDsl
fun HTMLTag.hxSwapOob(type: HxSwapType, selector: String) {
    attributes["hx-swap-oob"] = type.stringValue + ":" + selector
}

/**
 * https://htmx.org/attributes/hx-target/
 */
@HtmxDsl
fun HTMLTag.hxTarget(selector: String) {
    attributes["hx-target"] = selector
}

/**
 * https://htmx.org/attributes/hx-trigger/
 */
@HtmxDsl
fun HTMLTag.hxTrigger(value: String) {
    attributes["hx-trigger"] = value
}

/**
 * https://htmx.org/attributes/hx-vals/
 */
@HtmxDsl
fun HTMLTag.hxVals(json: String) {
    attributes["hx-vals"] = json
}

/**
 * https://htmx.org/attributes/hx-on/
 */
@HtmxDsl
fun HTMLTag.hxOn(event: String, script: String) {
    attributes["hx-on:$event"] = script
}

/**
 * https://htmx.org/attributes/hx-on/
 */
@HtmxDsl
fun HTMLTag.hxOn(event: HtmxEvent, script: String) {
    attributes["hx-on:" + event.htmlForm] = script
}

/**
 * https://htmx.org/attributes/hx-on/
 * @param event reference to an event via [HtmxEvents] method reference
 */
@HtmxDsl
fun HTMLTag.hxOn(event: () -> HtmxEvent, script: String) {
    attributes["hx-on:" + event().htmlForm] = script
}
