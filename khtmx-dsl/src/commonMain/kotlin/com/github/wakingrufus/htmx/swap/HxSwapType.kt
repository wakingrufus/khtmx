package com.github.wakingrufus.htmx.swap

enum class HxSwapType(val stringValue: String) {
    InnerHtml("innerHTML"),
    OuterHtml("outerHTML"),
    TextContent("textContent"),
    BeforeBegin("beforebegin"),
    AfterBegin("afterbegin"),
    BeforeEnd("beforeend"),
    AfterEnd("afterend"),
    Delete("delete"),
    None("none")
}
