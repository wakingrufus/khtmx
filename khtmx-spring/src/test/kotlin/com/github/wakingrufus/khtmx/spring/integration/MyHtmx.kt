package com.github.wakingrufus.khtmx.spring.integration

import com.github.wakingrufus.htmx.HttpVerb
import com.github.wakingrufus.khtmx.spring.SpringHtmxDsl
import kotlinx.html.div

class MyHtmx : SpringHtmxDsl({
    page("/index") {
        div {
            +"Hello"
        }
    }
    route(HttpVerb.GET, "/hello/{name}", Controller::sayHello) {
        div { +it.phrase }
    }
})
