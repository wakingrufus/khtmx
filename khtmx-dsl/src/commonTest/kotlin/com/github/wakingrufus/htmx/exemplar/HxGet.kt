package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import kotlinx.html.span
import kotlin.test.Test
import kotlin.test.assertEquals

class HxGet {
    @Test
    fun hxGet() {
        val actual = htmxExample {
            span {
                hxGet("/path")
            }
        }
        assertEquals(actual, """<span hx-get="/path"></span>""")
    }
}
