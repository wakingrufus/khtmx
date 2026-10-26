package com.github.wakingrufus.htmx.template

import kotlinx.html.li
import kotlinx.html.span
import kotlinx.html.stream.appendHTML
import kotlinx.html.ul
import kotlin.test.Test
import kotlin.test.assertEquals

class HtmxTemplateTest {
    @Test
    fun test_fragment() {
        val template = htmxTemplate<String> {
            span {
                +it
            }
        }
        val output = buildString {
            appendHTML(false).apply {
                template.render(this, "Hello")
            }
        }
        assertEquals("<span>Hello</span>", output)
    }

    @Test
    fun test_composable() {
        val itemTemplate = htmxTemplate<String> {
            li { +it }
        }
        val listTemplate = htmxTemplate<List<String>> {
            ul {
                it.forEach {
                    template(itemTemplate, it)
                }
            }
        }
        val output = buildString {
            appendHTML(false).template(listTemplate, listOf("Hello", "World"))
        }
        assertEquals("<ul><li>Hello</li><li>World</li></ul>", output)
    }
}
