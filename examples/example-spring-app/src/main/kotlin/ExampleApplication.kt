import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.EnableWebMvc


const val helloWorldUrl = "/load"

@EnableAutoConfiguration
@Import(ExampleBeans::class, ExampleHtmxDsl::class)
@EnableWebMvc
open class ExampleApplication

fun main(args: Array<String>) {
    SpringApplication.run(ExampleApplication::class.java, *args)
}
