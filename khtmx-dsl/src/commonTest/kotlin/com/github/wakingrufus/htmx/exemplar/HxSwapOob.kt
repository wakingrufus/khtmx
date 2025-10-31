package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxSwapOob
import com.github.wakingrufus.htmx.swap.HxSwapType
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.table
import kotlinx.html.tbody
import kotlinx.html.tr
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Replicates the HTMX example for [hx-swap](https://htmx.org/attributes/hx-swap/)
 */
class HxSwapOob {
    @Test
    fun hxSwapOob_example() {
        val actual = htmxExample {
            div {
                id = "alerts"
                hxSwapOob(true)
                +"Saved!"
            }
        }

        assertEquals("""<div id="alerts" hx-swap-oob="true">Saved!</div>""", actual)
    }

    @Test
    fun hxSwapOob_alternate_swap() {
        val actual = htmxExample {
            tbody {
                hxSwapOob(HxSwapType.BeforeEnd, "#table tbody")
                tr { }
            }
        }

        assertEquals("""<tbody hx-swap-oob="beforeend:#table tbody"><tr></tr></tbody>""", actual)
    }

    @Test
    fun hxSwapOob_alternate_swap_plain() {
        val actual = htmxExample {
            table {
                hxSwapOob(HxSwapType.BeforeEnd, "#table2")
                tr { }
            }
        }

        assertEquals("""<table hx-swap-oob="beforeend:#table2"><tr></tr></table>""", actual)
    }
}
