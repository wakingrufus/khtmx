package com.github.wakingrufus.khtmx.spring.route

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.wakingrufus.htmx.template.HtmxTemplate
import org.springframework.beans.factory.BeanRegistrarDsl.SupplierContextDsl
import org.springframework.beans.factory.ObjectProvider
import org.springframework.web.servlet.function.RouterFunctionDsl
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.security.Principal

interface HxRoute {
    fun registerRoutes(context: SupplierContextDsl<*>, dsl: RouterFunctionDsl)
}

fun <CONTROLLER : Any, REQ : Record, RESP : Any> withParam(
    routerFunction: RouterFunctionDsl.(String, (ServerRequest) -> ServerResponse) -> Unit,
    path: String,
    requestClass: Class<REQ>,
    controller: SupplierContextDsl<*>.() -> ObjectProvider<CONTROLLER>,
    objectMapper: SupplierContextDsl<*>.() -> ObjectProvider<ObjectMapper>,
    binding: CONTROLLER.(REQ) -> RESP,
    renderer: HtmxTemplate<RESP>
): HxRoute {
    return ParamRoute(routerFunction, path, requestClass, controller, objectMapper, binding, renderer)
}

fun <CONTROLLER : Any, RESP : Any> noParam(
    routerFunction: RouterFunctionDsl.(String, (ServerRequest) -> ServerResponse) -> Unit,
    path: String,
    controller: SupplierContextDsl<*>.() -> ObjectProvider<CONTROLLER>,
    binding: CONTROLLER.() -> RESP,
    renderer: HtmxTemplate<RESP>
): HxRoute {
    return NoParamRoute(routerFunction, path, controller, binding, renderer)
}


fun <CONTROLLER : Any, USER : Principal, RESP : Any> withAuth(
    routerFunction: RouterFunctionDsl.(String, (ServerRequest) -> ServerResponse) -> Unit,
    path: String,
    controller: SupplierContextDsl<*>.() -> ObjectProvider<CONTROLLER>,
    binding: CONTROLLER.(USER?) -> RESP,
    renderer: HtmxTemplate<RESP>
): HxRoute {
    return AuthRoute(routerFunction, path, controller, binding, renderer)
}


fun <CONTROLLER : Any, REQ : Record, USER : Principal, RESP : Any> withParamAndAuth(
    routerFunction: RouterFunctionDsl.(String, (ServerRequest) -> ServerResponse) -> Unit,
    path: String,
    requestClass: Class<REQ>,
    controller: SupplierContextDsl<*>.() -> ObjectProvider<CONTROLLER>,
    objectMapper: SupplierContextDsl<*>.() -> ObjectProvider<ObjectMapper>,
    binding: CONTROLLER.(USER?, REQ) -> RESP,
    renderer: HtmxTemplate<RESP>
): HxRoute {
    return ParamAuthRoute(routerFunction, path, requestClass, controller, objectMapper, binding, renderer)
}