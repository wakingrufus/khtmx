package com.github.wakingrufus.htmx.exemplar

import kotlinx.css.CssBuilder
import kotlinx.html.TagConsumer
import kotlinx.html.stream.appendHTML

fun htmxExample(example: TagConsumer<StringBuilder>.() -> Unit): String = buildString {
    appendHTML(false, true).apply {
        example()
    }
}

fun inlineCss(builder: CssBuilder.() -> Unit): String =
    CssBuilder().apply(builder).toString().replace("\n", " ").trimEnd()
