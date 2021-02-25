package com.gowid.leo.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.gowid.leo.common"])
@SpringBootApplication
class LeoApiApplication

fun main(args: Array<String>) {
    runApplication<LeoApiApplication>(*args)
}
