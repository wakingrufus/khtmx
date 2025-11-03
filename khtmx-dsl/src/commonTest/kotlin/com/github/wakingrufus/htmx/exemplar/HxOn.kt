package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.HtmxEvent
import com.github.wakingrufus.htmx.HtmxEvents.beforeRequest
import com.github.wakingrufus.htmx.hxGet
import com.github.wakingrufus.htmx.hxOn
import kotlinx.html.button
import kotlinx.html.div
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Replicates the HTMX example for [hx-swap](https://htmx.org/attributes/hx-swap/)
 */
class HxOn {
    @Test
    fun hxOn_example() {
        val actual = htmxExample {
            div {
                hxOn("click", """alert('Clicked!')""")
                +"Click"
            }
        }

        assertEquals("""<div hx-on:click="alert('Clicked!')">Click</div>""", actual)
    }

    @Test
    fun hxOn_example_htmx() {
        val actual = htmxExample {
            button {
                hxGet("/info")
                hxOn(HtmxEvent.BEFORE_REQUEST, """alert('Making a request!')""")
                +"Get Info!"
            }
        }

        assertEquals(
            //language=html
            """<button hx-get="/info" hx-on:htmx:before-request="alert('Making a request!')">Get Info!</button>""",
            actual
        )
    }

    @Test
    fun hxOn_example_htmx_double() {
        val actual = htmxExample {
            button {
                hxGet("/info")
                hxOn(::beforeRequest, """alert('Making a request!')""")
                +"Get Info!"
            }
        }

        assertEquals(
            //language=html
            """<button hx-get="/info" hx-on:htmx:before-request="alert('Making a request!')">Get Info!</button>""",
            actual
        )
    }
}
