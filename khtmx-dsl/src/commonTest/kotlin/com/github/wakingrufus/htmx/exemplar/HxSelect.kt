package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import com.github.wakingrufus.htmx.swap.HxSwapType
import kotlinx.html.button
import kotlin.test.Test
import kotlin.test.assertContains

/**
 * Replicates the HTMX example for [hx-select](https://htmx.org/attributes/hx-select/)
 */
class HxSelect {
    @Test
    fun hxSelect() {
        val actual = htmxExample {
            button {
                hxGet("/info") {
                    hxSelect = "#info-detail"
                    hxSwap {
                        style(HxSwapType.OuterHtml)
                    }
                }
                +"Get Info!"
            }
        }

        // attribute order is not guaranteed
        assertContains(actual, """hx-get="/info"""")
        assertContains(actual, """hx-select="#info-detail"""")
        assertContains(actual, """hx-swap="outerHTML"""")
    }
}
