package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxPost
import com.github.wakingrufus.htmx.hxTarget
import kotlinx.html.button
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Replicates the HTMX example for [hx-post](https://htmx.org/attributes/hx-post/)
 */
class HxPost {
    @Test
    fun hxPost() {
        val actual = htmxExample {
            button {
                hxPost("/account/enable") {
                    hxTarget("body")
                }
                +"Enable Your Account"
            }
        }
        assertEquals(
            actual,
            """<button hx-post="/account/enable" hx-target="body">Enable Your Account</button>"""
        )
    }
}
