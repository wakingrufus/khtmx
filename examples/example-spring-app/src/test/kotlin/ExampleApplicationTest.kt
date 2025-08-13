import io.github.oshai.kotlinlogging.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.test.client.TestRestTemplate
import org.springframework.boot.web.server.test.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.util.LinkedMultiValueMap
import java.util.UUID

@SpringBootTest(
    classes = [ExampleApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "logging.level.root = DEBUG",
        "logging.level.org.eclipse.jetty=INFO",
        "logging.level.jdk.event.security=INFO",
        "logging.level.org.springframework.web.servlet.function.support.RouterFunctionMapping  = TRACE"
    ]
)
class ExampleApplicationTest {

    private val log = KotlinLogging.logger {}

    @Autowired
    private lateinit var testRestTemplate: TestRestTemplate

    fun formData(formData: Map<String, String>): HttpEntity<LinkedMultiValueMap<String, String>> {
        val parameters: LinkedMultiValueMap<String, String> = LinkedMultiValueMap()
        formData.forEach { (key, value) -> parameters.add(key, value) }

        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val entity = HttpEntity(parameters, headers)
        return entity
    }

    @Test
    fun `test htmx`() {
        val response = testRestTemplate.exchange<String>(
            RequestEntity.get("/index")
                .accept(MediaType.TEXT_HTML)
                .build()
        )
        assertThat(response.statusCode.value()).isEqualTo(200)
        log.info { response.body }

        val formResponse = testRestTemplate.exchange<String>(
            RequestEntity.post("/load")
                .accept(MediaType.TEXT_HTML)
                .body(formData(mapOf("name" to "bob")))
        )
        log.info { formResponse.body }
        assertThat(formResponse.statusCode.value()).isEqualTo(200)
    }

    @Test
    fun `test json post`() {
        val formResponseJson = testRestTemplate.exchange<HelloWorldResponse>(
            RequestEntity.post("/load")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(HelloWorldRequest("bob"))
        )
        val responseString = formResponseJson.body
        log.info { responseString }
        assertThat(responseString).isEqualTo(HelloWorldResponse("Hello bob"))
    }

    @Test
    fun `test json post html response`() {
        val formResponseJson = testRestTemplate.exchange<String>(
            RequestEntity.post("/load")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN)
                .body(HelloWorldRequest("bob"))
        )
        val responseString = formResponseJson.body
        log.info { responseString }
        assertThat(responseString).isEqualTo("""<div><span>Hello bob</span></div>""")
    }

    @Test
    fun `test path param get`() {
        val pathParamResponse = testRestTemplate.exchange<String>(
            RequestEntity.get("/thing/${UUID.randomUUID()}")
                .accept(MediaType.TEXT_HTML)
                .build()
        )
        log.info {
            pathParamResponse.body
        }
        assertThat(pathParamResponse.statusCode.value()).isEqualTo(200)
    }
}