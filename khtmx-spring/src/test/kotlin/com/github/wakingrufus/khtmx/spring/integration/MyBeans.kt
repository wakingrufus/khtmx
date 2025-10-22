package com.github.wakingrufus.khtmx.spring.integration

import org.springframework.beans.factory.BeanRegistrarDsl

class MyBeans : BeanRegistrarDsl({
    registerBean<Controller>()
})
