package com.github.wakingrufus.htmx.target

import kotlin.test.Test
import kotlin.test.assertEquals

class SelectorDslTest {
    @Test
    fun test_this() {
        val actual = SelectorDsl().apply {
            `this`()
        }.invoke()
        assertEquals("this", actual)
    }

    @Test
    fun test_next() {
        val actual = SelectorDsl().apply {
            next()
        }.invoke()
        assertEquals("next", actual)
    }

    @Test
    fun test_find() {
        val actual = SelectorDsl().apply {
            find(".thing")
        }.invoke()
        assertEquals("find .thing", actual)
    }

    @Test
    fun test_closest() {
        val actual = SelectorDsl().apply {
            closest(".thing")
        }.invoke()
        assertEquals("closest .thing", actual)
    }

    @Test
    fun test_previous() {
        val actual = SelectorDsl().apply {
            previous()
        }.invoke()
        assertEquals("previous", actual)
    }

    @Test
    fun test_next_selector() {
        val actual = SelectorDsl().apply {
            next(".thing")
        }.invoke()
        assertEquals("next .thing", actual)
    }

    @Test
    fun test_previous_selector() {
        val actual = SelectorDsl().apply {
            previous(".thing")
        }.invoke()
        assertEquals("previous .thing", actual)
    }
}
