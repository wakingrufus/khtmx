package com.github.wakingrufus.htmx.exemplar

import com.github.wakingrufus.htmx.hxGet
import kotlinx.html.div
import kotlin.test.Test
import kotlin.test.assertEquals

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
        assertEquals(actual, """<div hx-get="/account" hx-push-url="true"></div>""")
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
        assertEquals(actual, """<div hx-get="/account" hx-push-url="/account/home"></div>""")
    }
}
