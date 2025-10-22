package com.github.wakingrufus.htmx

import com.github.wakingrufus.htmx.swap.HxSwapDsl
import kotlinx.html.HTMLTag

@HtmxDsl
fun HTMLTag.hx(verb: HttpVerb, path: String, dsl: HxDsl.() -> Unit = {}) {
    HxDsl(verb, path).apply(dsl).invoke(this)
}

@HtmxDsl
fun HTMLTag.hxSelect(selector: String) {
    attributes["hx-select"] = selector
}

@HtmxDsl
fun HTMLTag.hxSelectOob(selector: String) {
    attributes["hx-select-oob"] = selector
}

@HtmxDsl
fun HTMLTag.hxParams(params: String) {
    attributes["hx-params"] = params
}

@HtmxDsl
fun HTMLTag.hxPushUrl(value: Boolean) {
    attributes["hx-push-url"] = value.toString()
}

@HtmxDsl
fun HTMLTag.hxPushUrl(value: String) {
    attributes["hx-push-url"] = value
}

@HtmxDsl
fun HTMLTag.hxSwap(value: String) {
    attributes["hx-swap"] = value
}

@HtmxDsl
fun HTMLTag.hxSwap(dsl: HxSwapDsl.() -> Unit = {}) {
    attributes["hx-swap"] = HxSwapDsl().apply(dsl).invoke().invoke()
}

@HtmxDsl
fun HTMLTag.hxTarget(selector: String) {
    attributes["hx-target"] = selector
}

@HtmxDsl
fun HTMLTag.hxTrigger(value: String) {
    attributes["hx-trigger"] = value
}

@HtmxDsl
fun HTMLTag.hxVals(json: String) {
    attributes["hx-vals"] = json
}

@HtmxDsl
fun HTMLTag.hxPost(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.POST, path, dsl)
}

@HtmxDsl
fun HTMLTag.hxGet(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.GET, path, dsl)
}

@HtmxDsl
fun HTMLTag.hxPut(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.PUT, path, dsl)
}

@HtmxDsl
fun HTMLTag.hxPatch(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.PATCH, path, dsl)
}

@HtmxDsl
fun HTMLTag.hxDelete(path: String, dsl: HxDsl.() -> Unit = {}) {
    hx(HttpVerb.DELETE, path, dsl)
}

@HtmxDsl
fun HTMLTag.htmxIndicator() {
    attributes["class"] = "htmx-indicator"
}
