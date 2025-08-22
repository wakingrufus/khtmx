package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import kotlinx.html.div
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Replicates the HTMX example for [hx-push-url](https://htmx.org/attributes/hx-push-url/)
 */
class HxPushUrl {
    @Test
    fun hxPushUrl_true() {
        val actual = htmxExample {
            div {
                hxGet("/account") {
                    hxPushUrl(true)
                }
            }
        }
        assertEquals("""<div hx-get="/account" hx-push-url="true"></div>""", actual)
    }

    @Test
    fun hxPushUrl_custom_url() {
        val actual = htmxExample {
            div {
                hxGet("/account") {
                    hxPushUrl("/account/home")
                }
            }
        }
        assertEquals("""<div hx-get="/account" hx-push-url="/account/home"></div>""", actual)
    }
}
