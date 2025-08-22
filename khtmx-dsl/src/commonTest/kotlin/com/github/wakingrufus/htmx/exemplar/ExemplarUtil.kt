package com.github.wakingrufus.htmx.exemplar

import kotlinx.html.TagConsumer
import kotlinx.html.stream.appendHTML

fun htmxExample(example: TagConsumer<StringBuilder>.() -> Unit): String = buildString {
    appendHTML(false).apply {
        example()
    }
}
