package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import kotlinx.html.div
import kotlin.test.Test
import kotlin.test.assertContains

/**
 * Replicates the HTMX example for [hx-vals](https://htmx.org/attributes/hx-vals/)
 */
class HxVals {
    @Test
    fun hxVals_example() {
        val actual = htmxExample {
            div {
                hxGet("/example") {
                    hxVals("""{"myVal": "My Value"}""")
                }
            }
        }

        // attribute order is not guaranteed
        assertContains(actual, """hx-get="/example"""")
        assertContains(actual, """hx-vals="{&quot;myVal&quot;: &quot;My Value&quot;}"""")
    }
}
