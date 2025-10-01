package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxDelete
import kotlinx.html.button
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Replicates the HTMX example for [hx-delete](https://htmx.org/attributes/hx-delete/)
 */
class HxDelete {
    @Test
    fun hxDelete() {
        val actual = htmxExample {
            button {
                hxDelete("/account") {
                    hxTarget("body")
                }
                +"Delete Your Account"
            }
        }
        assertEquals("""<button hx-delete="/account" hx-target="body">Delete Your Account</button>""", actual)
    }
}
