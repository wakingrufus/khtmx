package com.github.wakingrufus.khtmx.spring

import org.springframework.web.servlet.function.ServerRequest
import tools.jackson.databind.json.JsonMapper

inline fun <reified REQ : Record> JsonMapper.deserializeForm(request: ServerRequest): REQ {
    return convertValue(request.params().toSingleValueMap(), REQ::class.java)
}

inline fun <reified REQ : Record> deserializeJson(request: ServerRequest): REQ {
    return request.body(REQ::class.java)
}
