package com.github.wakingrufus.htmx

import com.github.wakingrufus.htmx.exemplar.htmxExample
import kotlinx.html.div
import kotlinx.html.span
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HtmxExtensionsTest {
    @Test
    fun test_hxGet() {
        val actual = htmxExample {
            span {
                hxGet("/path")
            }
        }
        assertEquals(actual, """<span hx-get="/path"></span>""")
    }

    @Test
    fun test_hxPost() {
        val actual = htmxExample {
            span {
                hxPost("/path")
            }
        }
        assertEquals(actual, """<span hx-post="/path"></span>""")
    }

    @Test
    fun test_hxPut() {
        val actual = htmxExample {
            span {
                hxPut("/path")
            }
        }
        assertEquals(actual, """<span hx-put="/path"></span>""")
    }

    @Test
    fun test_hxPatch() {
        val actual = htmxExample {
            span {
                hxPatch("/path")
            }
        }
        assertEquals(actual, """<span hx-patch="/path"></span>""")
    }

    @Test
    fun test_hxDelete() {
        val actual = htmxExample {
            span {
                hxDelete("/path")
            }
        }
        assertEquals(actual, """<span hx-delete="/path"></span>""")
    }

    @Test
    fun test_hxPushUrl() {
        val actual = htmxExample {
            div {
                hxPushUrl(true)
            }
        }
        assertEquals(actual, """<div hx-push-url="true"></div>""")
    }
}
