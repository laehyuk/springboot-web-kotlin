package com.gowid.leo.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LeoCommonApplication

fun main(args: Array<String>) {
    runApplication<LeoCommonApplication>(*args)
}
