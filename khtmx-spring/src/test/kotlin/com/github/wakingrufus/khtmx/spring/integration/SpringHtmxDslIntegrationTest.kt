package com.github.wakingrufus.khtmx.spring.integration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.test.client.TestRestTemplate
import org.springframework.boot.web.server.test.client.getForEntity

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [App::class]
)
class SpringHtmxDslIntegrationTest {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `test page route registration`() {
        val result = testRestTemplate.getForEntity<String>("/index")
        assertThat(result.statusCode.value()).isEqualTo(200)
        assertThat(result.body)
            .contains("""<div>Hello</div>""")
    }

    @Test
    fun `test route registration`() {
        val result = testRestTemplate.getForEntity<String>("/hello/John")
        assertThat(result.statusCode.value()).isEqualTo(200)
        assertThat(result.body)
            .contains("""<div>Hello, John</div>""")
    }
}
