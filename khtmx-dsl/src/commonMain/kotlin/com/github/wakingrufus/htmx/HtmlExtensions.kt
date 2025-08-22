package com.github.wakingrufus.htmx

import kotlinx.html.HTMLTag

@HtmxDsl
fun HTMLTag.hxTrigger(verb: HttpVerb, path: String, dsl: TriggerDsl.() -> Unit = {}) {
    TriggerDsl(verb, path).apply(dsl).invoke(this)
}

@HtmxDsl
fun HTMLTag.hxSelect(selector: String) {
    attributes["hx-select"] = selector
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
fun HTMLTag.hxTarget(selector: String) {
    attributes["hx-target"] = selector
}

@HtmxDsl
fun HTMLTag.hxVals(json: String) {
    attributes["hx-vals"] = json
}

@HtmxDsl
fun HTMLTag.hxPost(path: String, dsl: TriggerDsl.() -> Unit = {}) {
    hxTrigger(HttpVerb.POST, path, dsl)
}

@HtmxDsl
fun HTMLTag.hxGet(path: String, dsl: TriggerDsl.() -> Unit = {}) {
    hxTrigger(HttpVerb.GET, path, dsl)
}

@HtmxDsl
fun HTMLTag.hxPut(path: String, dsl: TriggerDsl.() -> Unit = {}) {
    hxTrigger(HttpVerb.PUT, path, dsl)
}

@HtmxDsl
fun HTMLTag.hxPatch(path: String, dsl: TriggerDsl.() -> Unit = {}) {
    hxTrigger(HttpVerb.PATCH, path, dsl)
}

@HtmxDsl
fun HTMLTag.hxDelete(path: String, dsl: TriggerDsl.() -> Unit = {}) {
    hxTrigger(HttpVerb.DELETE, path, dsl)
}

@HtmxDsl
fun HTMLTag.htmxIndicator() {
    attributes["class"] = "htmx-indicator"
}
