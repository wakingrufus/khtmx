---
layout: default
title: KHTMX DSL
nav_order: 2
has_children: false
---

# KHTMX DSL

KHTMX DSL is a multi-platform Kotlin DSL which extends the kotlinx HTML DSL to support HTMX. It currently supports Kotlin/JVM and Kotlin/JS, but more targets are planned.

## Getting Started
The DSL library provides extensions which can be used seamlessly within the HTML DSL. For example, in Kotlin/JVM:
```kotlin
createHTMLDocument().html {  
    head {  
    }    
    body {  
        span {
            hxGet("/path")
        }
    }  
}
```

A pure-kotlin (common) usage can look like:
```kotlin
buildString {  
    appendHTML(false).apply {  
        span {  
            hxGet("/path")  
        }  
    }
}
```

### Templates

Templates can be declared to facilitate reuse. The htmxTemplate function of type T when is a higher-order function that produces a function which takes a single parameter T and allows you to use it in an html/htmx snippet. For example:

```kotlin
val template = htmxTemplate<String> {  
    span {  
        +it  
    }
}  
val output = buildString {  
    appendHTML(false).apply {  
        template.render(this, "Hello")  
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

## API Support

| Core Attributes                                               | Supported | Example                                                                                                                                     |
| ------------------------------------------------------------- | --------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| [`hx-get`](https://htmx.org/attributes/hx-get/)               | ✅         | [Example](https://github.com/wakingrufus/khtmx/blob/main/khtmx-dsl/src/commonTest/kotlin/com/github/wakingrufus/htmx/exemplar/HxGet.kt)     |
| [`hx-post`](https://htmx.org/attributes/hx-post/)             | ✅         | [Example](https://github.com/wakingrufus/khtmx/blob/main/khtmx-dsl/src/commonTest/kotlin/com/github/wakingrufus/htmx/exemplar/HxPost.kt)    |
| [`hx-on*`](https://htmx.org/attributes/hx-on/)                | ❌         |                                                                                                                                             |
| [`hx-push-url`](https://htmx.org/attributes/hx-push-url/)     | ✅         | [Example](https://github.com/wakingrufus/khtmx/blob/main/khtmx-dsl/src/commonTest/kotlin/com/github/wakingrufus/htmx/exemplar/HxPushUrl.kt) |
| [`hx-select`](https://htmx.org/attributes/hx-select/)         | ❌         |                                                                                                                                             |
| [`hx-select-oob`](https://htmx.org/attributes/hx-select-oob/) | ❌         |                                                                                                                                             |
| [`hx-swap`](https://htmx.org/attributes/hx-swap/)             | ✅         | [Example](https://github.com/wakingrufus/khtmx/blob/main/khtmx-dsl/src/commonTest/kotlin/com/github/wakingrufus/htmx/exemplar/HxSwap.kt)    |
| [`hx-swap-oob`](https://htmx.org/attributes/hx-swap-oob/)     | ❌         |                                                                                                                                             |
| [`hx-target`](https://htmx.org/attributes/hx-target/)         | ✅         | [Example](https://github.com/wakingrufus/khtmx/blob/main/khtmx-dsl/src/commonTest/kotlin/com/github/wakingrufus/htmx/exemplar/HxTarget.kt)  |
| [`hx-trigger`](https://htmx.org/attributes/hx-trigger/)       | ✅         | [Example](https://github.com/wakingrufus/khtmx/blob/main/khtmx-dsl/src/commonTest/kotlin/com/github/wakingrufus/htmx/exemplar/HxTrigger.kt) |
| [`hx-vals`](https://htmx.org/attributes/hx-vals/)             | ✅         | [Example](https://github.com/wakingrufus/khtmx/blob/main/khtmx-dsl/src/commonTest/kotlin/com/github/wakingrufus/htmx/exemplar/HxVals.kt)    |

| Additional Attributes                                              | Supported  |
|--------------------------------------------------------------------|------------|
| [`hx-boost`](https://htmx.org/attributes/hx-boost/)                | ❌          |
| [`hx-confirm`](https://htmx.org/attributes/hx-confirm/)            | ❌          |
| [`hx-delete`](https://htmx.org/attributes/hx-delete/)              | ✅          |
| [`hx-disable`](https://htmx.org/attributes/hx-disable/)            | ❌          |
| [`hx-disabled-elt`](https://htmx.org/attributes/hx-disabled-elt/)  | ❌          |
| [`hx-disinherit`](https://htmx.org/attributes/hx-disinherit/)      | ❌          |
| [`hx-encoding`](https://htmx.org/attributes/hx-encoding/)          | ❌          |
| [`hx-ext`](https://htmx.org/attributes/hx-ext/)                    | ❌          |
| [`hx-headers`](https://htmx.org/attributes/hx-headers/)            | ❌          |
| [`hx-history`](https://htmx.org/attributes/hx-history/)            | ❌          |
| [`hx-history-elt`](https://htmx.org/attributes/hx-history-elt/)    | ❌          |
| [`hx-include`](https://htmx.org/attributes/hx-include/)            | ❌          |
| [`hx-indicator`](https://htmx.org/attributes/hx-indicator/)        | ❌          |
| [`hx-inherit`](https://htmx.org/attributes/hx-inherit/)            | ❌          |
| [`hx-params`](https://htmx.org/attributes/hx-params/)              | ✅          |
| [`hx-patch`](https://htmx.org/attributes/hx-patch/)                | ✅          |
| [`hx-preserve`](https://htmx.org/attributes/hx-preserve/)          | ❌          |
| [`hx-prompt`](https://htmx.org/attributes/hx-prompt/)              | ❌          |
| [`hx-put`](https://htmx.org/attributes/hx-put/)                    | ✅          |
| [`hx-replace-url`](https://htmx.org/attributes/hx-replace-url/)    | ❌          |
| [`hx-request`](https://htmx.org/attributes/hx-request/)            | ❌          |
| [`hx-sync`](https://htmx.org/attributes/hx-sync/)                  | ❌          |
| [`hx-validate`](https://htmx.org/attributes/hx-validate/)          | ❌          |
| [`hx-vars`](https://htmx.org/attributes/hx-vars/)                  | ❌          |

| HTMX Features                                       | Supported  |
|-----------------------------------------------------|------------|
| [Configuration](https://htmx.org/reference/#config) | ❌          |