package com.github.wakingrufus.htmx

import kotlinx.html.span
import kotlinx.html.stream.appendHTML
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HtmxExtensionsTest {
    @Test
    fun test_hxGet() {
        val actual = buildString {
            appendHTML(false).apply {
                span {
                    hxGet("/path")
                }
            }
        }
        assertEquals(actual, """<span hx-get="/path"></span>""")
    }

    @Test
    fun test_hxPost() {
        val actual = buildString {
            appendHTML(false).apply {
                span {
                    hxPost("/path")
                }
            }
        }
        assertEquals(actual, """<span hx-post="/path"></span>""")
    }

    @Test
    fun test_hxPut() {
        val actual = buildString {
            appendHTML(false).apply {
                span {
                    hxPut("/path")
                }
            }
        }
        assertEquals(actual, """<span hx-put="/path"></span>""")
    }

    @Test
    fun test_hxPatch() {
        val actual = buildString {
            appendHTML(false).apply {
                span {
                    hxPatch("/path")
                }
            }
        }
        assertEquals(actual, """<span hx-patch="/path"></span>""")
    }

    @Test
    fun test_hxDelete() {
        val actual = buildString {
            appendHTML(false).apply {
                span {
                    hxDelete("/path")
                }
            }
        }
        assertEquals(actual, """<span hx-delete="/path"></span>""")
    }
}