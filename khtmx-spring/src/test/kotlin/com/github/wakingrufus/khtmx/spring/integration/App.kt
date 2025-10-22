package com.github.wakingrufus.khtmx.spring.integration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@Import(MyBeans::class, MyHtmx::class)
@EnableWebMvc
open class App
