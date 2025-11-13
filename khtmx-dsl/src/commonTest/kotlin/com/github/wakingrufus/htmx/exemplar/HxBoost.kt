package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxBoost
import kotlinx.html.FormMethod
import kotlinx.html.InputType
import kotlinx.html.a
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.input
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Replicates the HTMX example for [hx-boost](https://htmx.org/attributes/hx-boost/)
 */
class HxBoost {
    @Test
    fun hxBoost_true() {
        val actual = htmxExample {
            div {
                hxBoost(true)
                a(href = "/page1") { +"Go To Page 1" }
                a(href = "/page2") { +"Go To Page 2" }
            }
        }
        assertEquals(
            """<div hx-boost="true"><a href="/page1">Go To Page 1</a><a href="/page2">Go To Page 2</a></div>""",
            actual
        )
    }

    @Test
    fun hxBoost_form() {
        val actual = htmxExample {
            form(action = "/example", method = FormMethod.post) {
                hxBoost(true)
                input(name = "email", type = InputType.email) { placeholder = "Enter email..." }
                button { +"Submit" }
            }
        }
        assertTrue(actual.contains("""hx-boost="true""""))
    }
}
