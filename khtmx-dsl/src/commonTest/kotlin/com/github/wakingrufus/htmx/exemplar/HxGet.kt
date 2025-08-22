package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import kotlinx.html.button
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Replicates the HTMX example for [hx-get](https://htmx.org/attributes/hx-get/)
 */
class HxGet {
    @Test
    fun hxGet() {
        val actual = htmxExample {
            button {
                hxGet("/example")
                +"Get Some HTML"
            }
        }
        assertEquals("""<button hx-get="/example">Get Some HTML</button>""", actual)
    }
}
