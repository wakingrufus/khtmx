package com.github.wakingrufus.khtmx.spring.integration

class Controller {
    @JvmRecord
    data class Request(val name: String)

    @JvmRecord
    data class Response(val phrase: String)

    fun sayHello(request: Request): Response {
        return Response("Hello, " + request.name)
    }
}
