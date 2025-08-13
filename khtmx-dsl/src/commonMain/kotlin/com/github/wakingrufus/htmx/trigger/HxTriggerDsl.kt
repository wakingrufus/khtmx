package com.github.wakingrufus.htmx.trigger

import com.github.wakingrufus.htmx.HtmxDsl
import kotlin.time.Duration

@HtmxDsl
class HxTriggerDsl {
    private val events: MutableSet<HxTriggerEvent> = mutableSetOf()

    @HtmxDsl
    fun event(name: String, dsl: StandardEventDsl.() -> Unit = {}) {
        events.add(StandardEventDsl(name).apply(dsl)())
    }

    @HtmxDsl
    fun poll(duration: Duration) {
        events.add(HxTriggerEvent.Poll(duration))
    }

    @HtmxDsl
    fun load() {
        events.add(HxTriggerEvent.Load)
    }

    @HtmxDsl
    fun revealed() {
        events.add(HxTriggerEvent.Revealed)
    }

    @HtmxDsl
    fun intersect(dsl: IntersectDsl.() -> Unit) {
        events.add(IntersectDsl().apply(dsl)())
    }

    operator fun invoke(): HxTrigger {
        return HxTrigger(events)
    }
}