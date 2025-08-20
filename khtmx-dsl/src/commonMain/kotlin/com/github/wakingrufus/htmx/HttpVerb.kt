package com.github.wakingrufus.htmx

enum class HttpVerb(val attrName: String) {
    GET("get"),
    POST("post"),
    PUT("put"),
    PATCH("patch"),
    DELETE("delete")
}
