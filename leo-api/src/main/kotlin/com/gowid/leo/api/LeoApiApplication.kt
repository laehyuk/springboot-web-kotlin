package com.gowid.leo.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LeoApiApplication

fun main(args: Array<String>) {
    runApplication<LeoApiApplication>(*args)
}
