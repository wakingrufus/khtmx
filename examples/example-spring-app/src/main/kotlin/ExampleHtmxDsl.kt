import com.github.wakingrufus.htmx.HttpVerb
import com.github.wakingrufus.htmx.hxPost
import com.github.wakingrufus.htmx.swap.HxSwapType
import com.github.wakingrufus.htmx.template.htmxTemplate
import com.github.wakingrufus.khtmx.spring.SpringHtmxDsl
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.span
import kotlinx.html.textInput

val responseTemplate = htmxTemplate<HelloWorldResponse> {
    div {
        span {
            +it.message
        }
    }
}

class ExampleHtmxDsl : SpringHtmxDsl({
    page("/index") {
        form {
            hxPost(helloWorldUrl) {
                swap(HxSwapType.OuterHtml) {
                }
            }
            textInput(name = "name") {
            }
            button(classes = "btn primary") {
                +"Submit"
            }
        }
        button {
            hxPost(helloWorldUrl) {
                swap(HxSwapType.OuterHtml)
            }
            +"Click Me"
        }
    }
    route(HttpVerb.POST, helloWorldUrl, ExampleService::sayHello, responseTemplate)
    route(HttpVerb.GET, "thing/{id}", ExampleService::getThingById, responseTemplate)
})