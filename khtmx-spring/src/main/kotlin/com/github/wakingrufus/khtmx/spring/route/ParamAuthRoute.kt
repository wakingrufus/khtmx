package com.github.wakingrufus.khtmx.spring.route

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.wakingrufus.htmx.template.HtmxTemplate
import com.github.wakingrufus.htmx.template.template
import kotlinx.html.stream.appendHTML
import org.springframework.beans.factory.BeanRegistrarDsl.SupplierContextDsl
import org.springframework.beans.factory.ObjectProvider
import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.web.servlet.function.RouterFunctionDsl
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.contentTypeOrNull
import org.springframework.web.servlet.function.principalOrNull
import java.security.Principal

class ParamAuthRoute<CONTROLLER : Any, REQ : Record, USER : Principal, RESP : Any>(
    val routerFunction: RouterFunctionDsl.(String, (ServerRequest) -> ServerResponse) -> Unit,
    val path: String,
    private val requestClass: Class<REQ>,
    private val controller: SupplierContextDsl<*>.() -> ObjectProvider<CONTROLLER>,
    private val objectMapper: SupplierContextDsl<*>.() -> ObjectProvider<ObjectMapper>,
    val binding: CONTROLLER.(USER?, REQ) -> RESP,
    val renderer: HtmxTemplate<RESP>
) : HxRoute {
    override fun registerRoutes(context: SupplierContextDsl<*>, dsl: RouterFunctionDsl) {
        dsl.apply {
            routerFunction(path) { request ->
                val contentType = request.headers().contentTypeOrNull()
                val req = if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType)) {
                    request.body(requestClass)
                } else {
                    objectMapper.invoke(context).getObject().convertValue(
                        MultiValueMap.fromMultiValue(
                            request.params()
                                .plus(MultiValueMap.fromSingleValue(request.pathVariables()))
                        ).toSingleValueMap(),
                        requestClass
                    )
                }
                val resp = controller.invoke(context).getObject().binding(request.principalOrNull() as USER?, req)

                val acceptedType = request.headers().accept()
                if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType) && acceptedType.any {
                        MediaType.APPLICATION_JSON.isCompatibleWith(it)
                    }) {
                    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(resp)
                } else {
                    ServerResponse.ok().contentType(MediaType.TEXT_HTML)
                        .body(buildString {
                            appendHTML(false).template(renderer, resp)
                        })
                }
            }
        }
    }
}
