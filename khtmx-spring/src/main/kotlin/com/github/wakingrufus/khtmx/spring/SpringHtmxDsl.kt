package com.github.wakingrufus.khtmx.spring

import com.github.wakingrufus.htmx.HtmxDsl
import com.github.wakingrufus.htmx.HttpVerb
import com.github.wakingrufus.htmx.template.HtmxTemplate
import com.github.wakingrufus.khtmx.spring.route.*
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.beans.factory.BeanRegistry
import org.springframework.beans.factory.ObjectProvider
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.RouterFunctionDsl
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import tools.jackson.databind.json.JsonMapper
import java.security.Principal

@HtmxDsl
open class SpringHtmxDsl(init: SpringHtmxDsl.() -> Unit) : BeanRegistrarDsl({}) {
    val pages = ArrayList<HtmxPage>()
    internal var routes: MutableList<HxRoute> = mutableListOf()

    inline fun <reified T : Any> getBean(): (SupplierContextDsl<*>) -> ObjectProvider<T> = {
        it.beanProvider<T>()
    }

    init {
        init()
    }

    override fun register(registry: BeanRegistry, env: Environment) {
        super.register(registry, env)
        registerBean("khtmxSpring") {
            router {
                this@SpringHtmxDsl.registerRoutes(this@registerBean, this)
            }
        }
    }

    @HtmxDsl
    fun page(path: String, template: BODY.() -> Unit = {}) {
        pages.add(HtmxPage(path).apply { initialLoad(template) })
    }


    fun addRoute(route: HxRoute) {
        routes.add(route)
    }

    /**
     * use when declaring a GET route that takes no parameters
     */
    @HtmxDsl
    inline fun <reified CONTROLLER : Any, RESP : Any> get(
        path: String,
        noinline binding: CONTROLLER.() -> RESP,
        renderer: HtmxTemplate<RESP>
    ): HxRoute {
        return noParam(
            RouterFunctionDsl::GET,
            path = path,
            controller = getBean<CONTROLLER>(),
            binding = binding,
            renderer = renderer
        )
            .also { addRoute(it) }
    }

    @HtmxDsl
    inline fun <reified CONTROLLER : Any, RESP : Any> get(
        path: String,
        noinline binding: CONTROLLER.() -> RESP,
        crossinline renderer: TagConsumer<*>.(RESP) -> Unit
    ): HxRoute {
        return get(path, binding) { appendable, input -> renderer.invoke(appendable, input) }
    }

    /**
     * use when declaring a GET route that takes no parameters, but uses security
     */
    @HtmxDsl
    inline fun <reified CONTROLLER : Any, USER : Principal, RESP : Any> get(
        path: String,
        noinline binding: CONTROLLER.(USER?) -> RESP,
        renderer: HtmxTemplate<RESP>
    ): HxRoute {
        return withAuth(
            RouterFunctionDsl::GET,
            path = path,
            controller = getBean<CONTROLLER>(),
            binding = binding,
            renderer = renderer
        )
            .also { addRoute(it) }
    }

    @HtmxDsl
    inline fun <reified CONTROLLER : Any, USER : Principal, RESP : Any> get(
        path: String,
        noinline binding: CONTROLLER.(USER?) -> RESP,
        crossinline renderer: TagConsumer<*>.(RESP) -> Unit
    ): HxRoute {
        return get(path, binding) { appendable, input -> renderer.invoke(appendable, input) }
    }

    @HtmxDsl
    inline fun <reified CONTROLLER : Any, reified REQ : Record, RESP : Any> route(
        verb: HttpVerb,
        path: String,
        noinline binding: CONTROLLER.(REQ) -> RESP,
        crossinline renderer: TagConsumer<*>.(RESP) -> Unit
    ): HxRoute {
        return route(verb, path, binding) { appendable, input -> renderer.invoke(appendable, input) }
    }

    @HtmxDsl
    inline fun <reified CONTROLLER : Any, reified REQ : Record, RESP : Any> route(
        verb: HttpVerb,
        path: String,
        noinline binding: CONTROLLER.(REQ) -> RESP,
        renderer: HtmxTemplate<RESP>
    ): HxRoute {
        return withParam(
            routerFunction = when (verb) {
                HttpVerb.GET -> RouterFunctionDsl::GET
                HttpVerb.POST -> RouterFunctionDsl::POST
                HttpVerb.PUT -> RouterFunctionDsl::PUT
                HttpVerb.PATCH -> RouterFunctionDsl::PATCH
                HttpVerb.DELETE -> RouterFunctionDsl::DELETE
            },
            path = path,
            requestClass = REQ::class.java,
            controller = getBean<CONTROLLER>(),
            objectMapper = getBean<JsonMapper>(),
            binding = binding,
            renderer = renderer
        )
            .also { addRoute(it) }
    }

    @HtmxDsl
    inline fun <reified CONTROLLER : Any, reified REQ : Record, USER : Principal, RESP : Any> route(
        verb: HttpVerb,
        path: String,
        noinline binding: CONTROLLER.(USER?, REQ) -> RESP,
        crossinline renderer: TagConsumer<*>.(RESP) -> Unit
    ): HxRoute {
        return route(verb, path, binding) { appendable, input -> renderer.invoke(appendable, input) }
    }

    @HtmxDsl
    inline fun <reified CONTROLLER : Any, reified REQ : Record, USER : Principal, RESP : Any> route(
        verb: HttpVerb,
        path: String,
        noinline binding: CONTROLLER.(USER?, REQ) -> RESP,
        renderer: HtmxTemplate<RESP>
    ): HxRoute {
        return withParamAndAuth(
            routerFunction = when (verb) {
                HttpVerb.GET -> RouterFunctionDsl::GET
                HttpVerb.POST -> RouterFunctionDsl::POST
                HttpVerb.PUT -> RouterFunctionDsl::PUT
                HttpVerb.PATCH -> RouterFunctionDsl::PATCH
                HttpVerb.DELETE -> RouterFunctionDsl::DELETE
            },
            path = path,
            requestClass = REQ::class.java,
            controller = getBean<CONTROLLER>(),
            objectMapper = getBean<JsonMapper>(),
            binding = binding,
            renderer = renderer
        )
            .also { addRoute(it) }
    }

    fun registerRoutes(context: SupplierContextDsl<*>, dsl: RouterFunctionDsl) {
        dsl.apply {
            pages.forEach {
                GET(it.path) { request ->
                    ServerResponse.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(
                            createHTMLDocument().html {
                                head {
                                    script {
                                        src = "https://unpkg.com/htmx.org@2.0.4"
                                    }
                                }
                                body {
                                    it.initialLoad.invoke(this)
                                }
                            }.serialize(false)
                        )
                }
            }
            routes.forEach { route ->
                route.registerRoutes(context, this)
            }
        }
    }
}
