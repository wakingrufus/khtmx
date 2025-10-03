package com.github.wakingrufus.khtmx.spring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.servlet.function.ServerRequest
import tools.jackson.databind.json.JsonMapper
import java.nio.charset.StandardCharsets

@JvmRecord
data class TestRecord(val name: String)
internal class DeserializerTest {

    @Test
    fun `test form`() {
        val objectMapper = JsonMapper()
        val request = MockHttpServletRequest()
        request.addParameter("name", "test")
        request.contentType = MediaType.TEXT_HTML_VALUE
        val actual = objectMapper.deserializeForm<TestRecord>(
            ServerRequest.create(request, listOf(JacksonJsonHttpMessageConverter(), FormHttpMessageConverter()))
        )
        assertThat(actual).isEqualTo(TestRecord(name = "test"))
    }

    @Test
    fun `test json`() {
        val request = MockHttpServletRequest()
        request.setContent("""{"name": "test"}""".toByteArray(StandardCharsets.UTF_8))
        request.contentType = "application/json"
        val actual = deserializeJson<TestRecord>(
            ServerRequest.create(request, listOf(JacksonJsonHttpMessageConverter()))
        )
        assertThat(actual).isEqualTo(TestRecord(name = "test"))
    }
}
