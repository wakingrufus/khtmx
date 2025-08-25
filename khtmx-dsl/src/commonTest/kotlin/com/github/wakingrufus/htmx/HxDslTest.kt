package com.github.wakingrufus.htmx

import com.github.wakingrufus.htmx.swap.HxSwapType
import kotlinx.html.div
import kotlinx.html.stream.appendHTML
import kotlin.test.Test
import kotlin.test.assertEquals

class HxDslTest {
    @Test
    fun test_default_params() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/")(this)
            }
        }
        assertEquals(actual, """<div hx-get="/"></div>""")
    }

    @Test
    fun test_no_params() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/").apply {
                    noParams()
                }(this)
            }
        }
        assertEquals(actual, """<div hx-get="/" hx-params="none"></div>""")
    }

    @Test
    fun test_all_params() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/").apply {
                    allParams()
                }(this)
            }
        }
        assertEquals(actual, """<div hx-get="/" hx-params="*"></div>""")
    }

    @Test
    fun test_include_params() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/").apply {
                    includeParams("1", "2")
                }(this)
            }
        }
        assertEquals(actual, """<div hx-get="/" hx-params="1,2"></div>""")
    }

    @Test
    fun test_exclude_params() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/").apply {
                    excludeParams("1", "2")
                }(this)
            }
        }
        assertEquals(actual, """<div hx-get="/" hx-params="not 1,2"></div>""")
    }

    @Test
    fun test_target() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/").apply {
                    hxTarget("body")
                }(this)
            }
        }
        assertEquals(actual, """<div hx-get="/" hx-target="body"></div>""")
    }

    @Test
    fun test_push_url() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/").apply {
                    hxPushUrl(true)
                }(this)
            }
        }
        assertEquals(actual, """<div hx-get="/" hx-push-url="true"></div>""")
    }

    @Test
    fun test_swap() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/").apply {
                    hxSwap {
                        style(HxSwapType.OuterHtml)
                    }
                }(this)
            }
        }
        assertEquals(actual, """<div hx-get="/" hx-swap="outerHTML"></div>""")
    }

    @Test
    fun test_select() {
        val actual = buildString {
            appendHTML(false).div {
                HxDsl(HttpVerb.GET, "/").apply {
                    hxSelect = "#id"
                }(this)
            }
        }
        assertEquals(actual, """<div hx-get="/" hx-select="#id"></div>""")
    }
}
