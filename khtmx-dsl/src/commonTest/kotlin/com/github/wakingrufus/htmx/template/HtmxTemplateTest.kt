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
        assertEquals(output, "<span>Hello</span>")
    }

//    @Test
//    fun test_document() {
//        val template = htmxTemplate<String> {
//            span {
//                +it
//            }
//        }
//
//        val output = createHTMLDocument().html {
//            head {
//            }
//            body {
//                template.render(consumer, "Hello")
//            }
//        }.serialize(false)
//        assertThat(output)
//            .isEqualTo(
//                "<!DOCTYPE html>\n" +
//                        "<html>" +
//                        "<head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>" +
//                        "<body><span>Hello</span></body>" +
//                        "</html>"
//            )
//    }

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
        assertEquals(output, "<ul><li>Hello</li><li>World</li></ul>")
    }
}
