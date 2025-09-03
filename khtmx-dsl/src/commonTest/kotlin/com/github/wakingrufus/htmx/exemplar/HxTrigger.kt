package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import kotlinx.html.div
import kotlinx.html.input
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

/**
 * Replicates the HTMX example for [hx-trigger](https://htmx.org/attributes/hx-trigger/)
 */
class HxTrigger {
    @Test
    fun hxTrigger_click() {
        val actual = htmxExample {
            div {
                hxGet("/clicked") {
                    hxTrigger {
                        event("click")
                    }
                }
                +"Click Me"
            }
        }
        assertEquals("""<div hx-get="/clicked" hx-trigger="click">Click Me</div>""", actual)
    }

    @Test
    fun hxTrigger_click_filter() {
        val actual = htmxExample {
            div {
                hxGet("/clicked") {
                    hxTrigger {
                        event("click") {
                            filter("ctrlKey")
                        }
                    }
                }
                +"Control Click Me"
            }
        }
        assertEquals("""<div hx-get="/clicked" hx-trigger="click[ctrlKey]">Control Click Me</div>""", actual)
    }

    @Test
    fun hxTrigger_changed_delay() {
        val actual = htmxExample {
            input(name = "q") {
                hxGet("/search") {
                    hxTrigger {
                        event("input") {
                            changed()
                            delay(1.seconds)
                        }
                    }
                    hxTarget("#search-results")
                }
            }
        }
        assertEquals(
            """<input name="q" hx-get="/search" hx-trigger="input changed delay:1s" hx-target="#search-results"/>""",
            actual
        )
    }

    @Test
    fun hxTrigger_polling() {
        val actual = htmxExample {
            div {
                hxGet("/latest_updates") {
                    hxTrigger {
                        poll(1.seconds)
                    }
                }
                +"Nothing Yet!"
            }
        }
        assertEquals("""<div hx-get="/latest_updates" hx-trigger="every 1s">Nothing Yet!</div>""", actual)
    }

    @Test
    fun hxTrigger_multiple_triggers() {
        val actual = htmxExample {
            div {
                hxGet("/news") {
                    hxTrigger {
                        load()
                        event("click") {
                            delay(1.seconds)
                        }
                    }
                }
            }
        }
        assertEquals("""<div hx-get="/news" hx-trigger="load, click delay:1s"></div>""", actual)
    }
}
