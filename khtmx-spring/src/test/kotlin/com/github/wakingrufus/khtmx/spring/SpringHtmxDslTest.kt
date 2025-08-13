package com.github.wakingrufus.khtmx.spring

import com.github.wakingrufus.htmx.HttpVerb
import kotlinx.html.div
import kotlinx.html.span
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.mock.web.SpringBootMockServletContext
import org.springframework.boot.web.server.servlet.context.ServletWebServerApplicationContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.security.Principal
import java.util.UUID

class SpringHtmxDslTest {
    @JvmRecord
    data class TestRequestClass(val id: UUID)

    @JvmRecord
    data class TestResponseClass(val id: UUID)

    @JvmRecord
    data class TestResponseNameClass(val name: String)
    class TestController {
        fun noParamGet(): TestResponseClass {
            return TestResponseClass(UUID.randomUUID())
        }

        fun get(req: TestRequestClass): TestResponseClass {
            return TestResponseClass(req.id)
        }

        fun auth(user: Principal?): TestResponseNameClass {
            return TestResponseNameClass(user?.name ?: "anonymous")
        }

        fun authAndParam(user: Principal?, req: TestRequestClass): TestResponseNameClass {
            return TestResponseNameClass(user?.name ?: req.id.toString())
        }
    }

    @Test
    fun `test no param get`() {
        val htmxPage = SpringHtmxDsl {
            get("", TestController::noParamGet) {
                span {
                    +it.id.toString()
                }
            }
        }
        assertThat(htmxPage.routes).hasSize(1)
    }

    @Test
    fun `test get with params`() {
        val htmxPage = SpringHtmxDsl {
            route(HttpVerb.GET, "", TestController::get) {
                span {
                    +it.id.toString()
                }
            }
        }
        assertThat(htmxPage.routes).hasSize(1)
    }

    @Test
    fun `test auth`() {
        val htmxPage = SpringHtmxDsl {
            get("", TestController::auth) {
                span {
                    +it.name
                }
            }
        }
        assertThat(htmxPage.routes).hasSize(1)
    }

    @Test
    fun `test auth and param`() {
        val htmxPage = SpringHtmxDsl {
            route(HttpVerb.GET, "", TestController::authAndParam) {
                span {
                    +it.name
                }
            }
        }
        assertThat(htmxPage.routes).hasSize(1)
    }

    @Test
    fun `test route registration`() {
        val htmxPage = SpringHtmxDsl {
            page("index") {
                div {
                    +"Hello"
                }
            }
        }
        val context = ServletWebServerApplicationContext().apply {
            servletContext = SpringBootMockServletContext("/")
        }
        context.register(htmxPage)
        context.refresh()
        val mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/index"))
            .andReturn()
        assertThat(result.response.status).isEqualTo(200)
        assertThat(result.response.contentAsString)
            .contains("""<div>Hello</div>""")
        context.close()
    }
}