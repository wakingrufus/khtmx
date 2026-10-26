package com.github.wakingrufus.khtmx.spring.integration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.TestRestTemplate
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate
import org.springframework.boot.resttestclient.getForEntity
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [App::class]
)
@AutoConfigureTestRestTemplate
class SpringHtmxDslIntegrationTest {
    @Test
    fun `test page route registration`(@Autowired testRestTemplate: TestRestTemplate) {
        val result = testRestTemplate.getForEntity<String>("/index")
        assertThat(result.statusCode.value()).isEqualTo(200)
        assertThat(result.body)
            .contains("""<div>Hello</div>""")
    }

    @Test
    fun `test route registration`(@Autowired testRestTemplate: TestRestTemplate) {
        val result = testRestTemplate.getForEntity<String>("/hello/John")
        assertThat(result.statusCode.value()).isEqualTo(200)
        assertThat(result.body)
            .contains("""<div>Hello, John</div>""")
    }
}
