import org.springframework.beans.factory.BeanRegistrarDsl

class ExampleBeans() : BeanRegistrarDsl({
    registerBean<ExampleService>(){
        ExampleService()
    }
})