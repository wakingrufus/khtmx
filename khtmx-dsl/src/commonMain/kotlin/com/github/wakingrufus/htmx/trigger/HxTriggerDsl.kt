package com.github.wakingrufus.htmx.trigger

import com.github.wakingrufus.htmx.HtmxDsl
import kotlin.time.Duration

@HtmxDsl
class HxTriggerDsl {
    private val events: MutableSet<HxTriggerEvent> = mutableSetOf()

    fun event(name: String, dsl: StandardEventDsl.() -> Unit = {}) {
        events.add(StandardEventDsl(name).apply(dsl)())
    }

    fun poll(duration: Duration) {
        events.add(HxTriggerEvent.Poll(duration))
    }

    fun load() {
        events.add(HxTriggerEvent.Load)
    }

    fun revealed() {
        events.add(HxTriggerEvent.Revealed)
    }

    fun intersect(dsl: IntersectDsl.() -> Unit) {
        events.add(IntersectDsl().apply(dsl)())
    }

    operator fun invoke(): HxTrigger = HxTrigger(events)
}
