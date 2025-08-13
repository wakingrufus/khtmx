package com.github.wakingrufus.khtmx.spring

import org.springframework.beans.factory.BeanFactory

fun interface KhtmxHandler {
    fun callBeanHandler(bf: BeanFactory, request: Record): Record
}

internal fun <CONTROLLER : Any, REQ : Record> beanAwareHandler(
    controllerClass: Class<CONTROLLER>,
    function: CONTROLLER.(REQ) -> Record
): KhtmxHandler {
    return KhtmxHandler { bf, request -> function.invoke(bf.getBean(controllerClass), request as REQ) }
}