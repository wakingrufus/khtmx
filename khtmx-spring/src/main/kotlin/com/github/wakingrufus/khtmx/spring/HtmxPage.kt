package com.github.wakingrufus.khtmx.spring

import com.github.wakingrufus.htmx.HtmxDsl
import com.github.wakingrufus.khtmx.spring.route.HxRoute
import kotlinx.html.BODY

class HtmxPage(val path: String) {
    var initialLoad: BODY.() -> Unit = {}
    internal var routes: MutableList<HxRoute> = mutableListOf()

    @HtmxDsl
    fun initialLoad(dsl: BODY.() -> Unit) {
        initialLoad = dsl
    }
}