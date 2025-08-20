package com.github.wakingrufus.htmx.template

import kotlinx.html.body
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.span
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class HtmxTemplateJvmTest {

    @Test
    fun test_document() {
        val template = htmxTemplate<String> {
            span {
                +it
            }
        }

        val output = createHTMLDocument().html {
            head {
            }
            body {
                template.render(consumer, "Hello")
            }
        }.serialize(false)
        assertThat(output)
            .isEqualTo(
                "<!DOCTYPE html>\n" +
                    "<html>" +
                    "<head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>" +
                    "<body><span>Hello</span></body>" +
                    "</html>"
            )
    }
}
