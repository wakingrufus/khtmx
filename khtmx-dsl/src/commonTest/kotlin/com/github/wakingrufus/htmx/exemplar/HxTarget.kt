package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxPost
import com.github.wakingrufus.htmx.swap.HxSwapType
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.id
import kotlin.test.Test
import kotlin.test.assertContains

/**
 * Replicates the HTMX example for [hx-target](https://htmx.org/attributes/hx-tar/)
 */
class HxTarget {
    @Test
    fun hxTarget_example() {
        val actual = htmxExample {
            div {
                div {
                    id = "response-div"
                }
                button {
                    hxPost("/register") {
                        hxTarget("#response-div")
                        hxSwap {
                            style(HxSwapType.BeforeEnd)
                        }
                    }
                    +"Register!"
                }
            }
        }

        // attribute order is not guaranteed
        assertContains(actual, """hx-post="/register"""")
        assertContains(actual, """hx-target="#response-div"""")
        assertContains(actual, """hx-swap="beforeend"""")
    }

    @Test
    fun hxTarget_example_this() {
        val actual = htmxExample {
            div {
                div {
                    id = "response-div"
                }
                button {
                    hxPost("/new-link") {
                        hxTarget {
                            `this`()
                        }
                        hxSwap {
                            style(HxSwapType.OuterHtml)
                        }
                    }
                    +"New link"
                }
            }
        }

        // attribute order is not guaranteed
        assertContains(actual, """hx-post="/new-link"""")
        assertContains(actual, """hx-target="this"""")
        assertContains(actual, """hx-swap="outerHTML"""")
    }
}
