package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxPost
import kotlinx.html.span
import kotlin.test.Test
import kotlin.test.assertEquals

class HxPost {
    @Test
    fun hxPost() {
        val actual = htmxExample {
            span {
                hxPost("/path")
            }
        }
        assertEquals(actual, """<span hx-post="/path"></span>""")
    }
}
