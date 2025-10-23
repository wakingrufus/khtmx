---
layout: default
title: KHTMX for Spring
nav_order: 3
has_children: false
---

# KHTMX for Spring

khtmx-spring provides a DSL for Spring Boot which allows a seamless integration between Spring Boot routes and HTMX pages and templates.

## Getting Started

This entry point for khtmx-spring is the `SpringHtmxDsl` class. 

### AutoConfiguration
To use `SpringHtmxDsl` in an Spring application with AutoConfiguration, extend this class, similar to `BeanRegistrarDsl`:
```kotlin
class ExampleHtmxDsl : SpringHtmxDsl({
    page("/index") {
        // HTMX DSL goes here
    }
    route(HttpVerb.GET, "thing/{id}", ExampleService::getThingById, responseTemplate)
})
```
Then, Import this class in your Application:

```kotlin
@EnableAutoConfiguration
@Import(ExampleHtmxDsl::class)
@EnableWebMvc
open class ExampleApplication
```

### ApplicationContextInitializer

To use `SpringHtmxDsl` via an `ApplicationContextInitializer`, it can just be registered as a `BeanRegistrarDsl`:
```kotlin
context.register(SpringHtmxDsl({
    page("/index") {
        // HTMX DSL goes here
    }
    route(HttpVerb.GET, "thing/{id}", ExampleService::getThingById, responseTemplate)
}))
```

### Page

Each discrete "page" or "SPA" you would like to build starts with an HTMX "page". These are declared in the DSL with the url route to the page you would like to use.

```kotlin

class ExampleHtmxDsl : SpringHtmxDsl({
    page("/index") {
        // HTMX DSL goes here
    }
})
```

You must declare the "landing page" for each page, which is the initial content to show on load. Use the HTML DSL to define that view.

```kotlin
page("/index") {
    div {
        span { +"Welcome to the Page" }
        button {
            +"Go"
        }
    }
}
```

## Templates
Templates can be declared which define how a given binding response should be rendered to htmx.

```kotlin
val myTemplate = htmxTemplate<ResponseDataClass> {
    div {
        span {
            +it.name
        }
    }
}
```

Templates can be composed.

```kotlin
val itemTemplate = htmxTemplate<String> {
    li { +it }
}
val listTemplate = htmxTemplate<List<String>> {
    ul {
        it.forEach {
            template(itemTemplate, it)
        }
    }
}
```

## HTMX Interaction

This library provides extensions on the HTML DSL in order to declare HTMX interaction.

```kotlin
page("/index") {
    div {
        span { +"Welcome to the Page" }
        button {
            hxGet("/start") {
                swap(HxSwapType.OuterHtml)
            }
            +"Go"
        }
    }
}
```

Each interaction which binds to a backend route will need a route declared to handle the request.

## HTMX routes

Routes are server-side handers for handling HTMX interactions. They consist of 4 main components:

1. HttpVerb: the HTTP verb/method to listen for (eg, GET, POST, etc).
2. route: the url path
3. handler: a function reference to call. This method must take a single parameter. The incoming request from HTMX will be deserialized into this parameter, then passed to the function.
4. renderer: a template for rendering the result of the handler call in HTMX. renderers can be templates, or defined inline.

Each route can be invoked via an HTMX trigger, which will result in the renderer being invoked, and HTML returned. However, these routes will also support returning the JSON of the data object which would be passed to the template via the "application/json" accept header.

#### Example route (inline)
```kotlin
route(HttpVerb.POST, helloWorldUrl, ExampleService::sayHello) {
    div {
        span {
            +it.message
        }
    }
}
```

#### Example route (template)
```kotlin
val myTemplate = htmxTemplate<ResponseDataClass> {
    div {
        span {
            +it.message
        }
    }
}
route(HttpVerb.POST, helloWorldUrl, ExampleService::sayHello, myTemplate)
```

### GET
GET requests may have a parameter or no parameter.

#### GET requests with no parameter
```kotlin
get("things", TestController::getAll) {
    span {
        +it.id.toString()
    }
}
```

#### GET requests with a parameter
```kotlin
route(HttpVerb.GET, "thing/1", TestController::get) {
    span {
        +it.id.toString() 
    }
}
```