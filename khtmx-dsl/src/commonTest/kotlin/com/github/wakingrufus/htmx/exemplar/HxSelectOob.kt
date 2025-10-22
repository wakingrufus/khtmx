package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import com.github.wakingrufus.htmx.swap.HxSwapType
import kotlinx.html.button
import kotlin.test.Test
import kotlin.test.assertContains

/**
 * Replicates the HTMX example for [hx-select-oob](https://htmx.org/attributes/hx-select-oob/)
 */
class HxSelectOob {
    @Test
    fun hxSelectOob() {
        val actual = htmxExample {
            button {
                hxGet("/info") {
                    hxSelect = "#info-detail"
                    hxSelectOob = "#alert"
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
        assertContains(actual, """hx-select-oob="#alert"""")
    }
}
