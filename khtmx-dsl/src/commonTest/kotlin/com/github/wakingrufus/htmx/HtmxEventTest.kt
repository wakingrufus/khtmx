package com.github.wakingrufus.htmx

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

internal class HtmxEventTest {
    @Test
    fun test_html_form() {
        HtmxEvent.entries.forEach {
            assertEquals(
                it.htmlForm,
                it.htmlForm.lowercase(),
                "html form for $it does not contain upper case letters"
            )
        }
    }

    @Test
    fun test_js_form() {
        HtmxEvent.entries.forEach {
            assertFalse(
                it.jsForm.contains("-"),
                "js form for $it does not contain dashes"
            )
        }
    }
}
