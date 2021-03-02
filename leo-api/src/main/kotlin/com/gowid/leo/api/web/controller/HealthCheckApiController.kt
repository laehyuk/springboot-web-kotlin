package com.gowid.leo.api.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class HealthCheckApiController {

    @GetMapping("/health")
    fun healthCheck() : LocalDateTime? = LocalDateTime.now()

}