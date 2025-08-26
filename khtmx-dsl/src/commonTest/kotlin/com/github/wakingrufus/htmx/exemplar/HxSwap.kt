package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import com.github.wakingrufus.htmx.hxSwap
import com.github.wakingrufus.htmx.swap.HxSwapType
import kotlinx.css.Overflow
import kotlinx.css.height
import kotlinx.css.overflow
import kotlinx.css.px
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.style
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

/**
 * Replicates the HTMX example for [hx-swap](https://htmx.org/attributes/hx-swap/)
 */
class HxSwap {
    @Test
    fun hxSwap_example() {
        val actual = htmxExample {
            div {
                hxGet("/example") {
                    hxSwap {
                        style(HxSwapType.AfterEnd)
                    }
                }
                +"Get Some HTML & Append It"
            }
        }

        assertEquals("""<div hx-get="/example" hx-swap="afterend">Get Some HTML &amp; Append It</div>""", actual)
    }

    @Test
    fun hxSwap_swap_example() {
        val actual = htmxExample {
            div {
                hxGet("/example") {
                    hxSwap {
                        style(HxSwapType.InnerHtml)
                        swap(1.seconds)
                    }
                }
                +"Get Some HTML & Append It"
            }
        }

        assertEquals(
            """<div hx-get="/example" hx-swap="innerHTML swap:1s">Get Some HTML &amp; Append It</div>""",
            actual
        )
    }

    @Test
    fun hxSwap_settle_example() {
        val actual = htmxExample {
            div {
                hxGet("/example") {
                    hxSwap {
                        style(HxSwapType.InnerHtml)
                        settle(1.seconds)
                    }
                }
                +"Get Some HTML & Append It"
            }
        }

        assertEquals(
            """<div hx-get="/example" hx-swap="innerHTML settle:1s">Get Some HTML &amp; Append It</div>""",
            actual
        )
    }

    @Test
    fun hxSwap_scroll_example() {
        val actual = htmxExample {
            div {
                style = inlineCss {
                    height = 200.px
                    overflow = Overflow.scroll
                }
                hxGet("/example") {
                    hxSwap {
                        style(HxSwapType.BeforeEnd)
                        scroll(bottom)
                    }
                }
                +"Get Some HTML & Append It & Scroll To Bottom"
            }
        }

        assertEquals(
            """<div style="height: 200px; overflow: scroll;" hx-get="/example" hx-swap="beforeend scroll:bottom">Get Some HTML &amp; Append It &amp; Scroll To Bottom</div>""",
            actual
        )
    }

    @Test
    fun hxSwap_show_example() {
        val actual = htmxExample {
            div {
                hxGet("/example") {
                    hxSwap {
                        style(HxSwapType.BeforeEnd)
                        show(bottom)
                    }
                }
                +"Get Some HTML & Append It & Scroll To Bottom"
            }
        }

        assertEquals(
            """<div hx-get="/example" hx-swap="beforeend show:bottom">Get Some HTML &amp; Append It &amp; Scroll To Bottom</div>""",
            actual
        )
    }

    @Test
    fun hxSwap_show_target_example() {
        val actual = htmxExample {
            div {
                hxGet("/example") {
                    hxSwap {
                        style(HxSwapType.InnerHtml)
                        show("#another-div", top)
                    }
                }
                +"Get Some Content"
            }
        }

        assertEquals(
            """<div hx-get="/example" hx-swap="innerHTML show:#another-div:top">Get Some Content</div>""",
            actual
        )
    }

    @Test
    fun hxSwap_show_window_example() {
        val actual = htmxExample {
            div {
                hxGet("/example") {
                    hxSwap {
                        style(HxSwapType.InnerHtml)
                        show(window, top)
                    }
                }
                +"Get Some Content"
            }
        }

        assertEquals(
            """<div hx-get="/example" hx-swap="innerHTML show:window:top">Get Some Content</div>""",
            actual
        )
    }

    @Test
    fun hxSwap_show_none_example() {
        val actual = htmxExample {
            form {
                action = "/example"
                hxSwap {
                    show(none)
                }
                +"Get Some Content"
            }
        }

        assertEquals(
            """<form action="/example" hx-swap="show:none">Get Some Content</form>""",
            actual
        )
    }

    @Test
    fun hxSwap_focusScroll_true_example() {
        val actual = htmxExample {
            input {
                id = "name"
                hxGet("/validation") {
                    hxSwap {
                        style(HxSwapType.OuterHtml)
                        focusScroll(true)
                    }
                }
            }
        }

        assertEquals(
            """<input id="name" hx-get="/validation" hx-swap="outerHTML focus-scroll:true"/>""",
            actual
        )
    }

    @Test
    fun hxSwap_focusScroll_false_example() {
        val actual = htmxExample {
            input {
                id = "name"
                hxGet("/validation") {
                    hxSwap {
                        style(HxSwapType.OuterHtml)
                        focusScroll(false)
                    }
                }
            }
        }

        assertEquals(
            """<input id="name" hx-get="/validation" hx-swap="outerHTML focus-scroll:false"/>""",
            actual
        )
    }
}
