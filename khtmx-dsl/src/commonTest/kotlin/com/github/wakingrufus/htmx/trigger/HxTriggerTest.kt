package com.github.wakingrufus.htmx.trigger

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class HxTriggerTest {
    @Test
    fun test_poll() {
        val actual = HxTriggerDsl().apply {
            poll(10.seconds)
        }()
        assertEquals(actual(), "every 10s")
    }

    @Test
    fun test_standard_event() {
        val actual = HxTriggerDsl().apply {
            event("click") {
                once()
            }
        }()
        assertEquals(actual(), "click once")
    }

    @Test
    fun test_intersect_event() {
        val actual = HxTriggerDsl().apply {
            intersect {
                root("#home")
            }
        }()
        assertEquals(actual(), "intersect root:#home")
    }
}
