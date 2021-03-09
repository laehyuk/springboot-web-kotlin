package com.gowid.leo.api.config

import com.gowid.leo.api.config.auth.LoginUserArgumentResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class WebConfig(
        private val loginUserArgumentResolver: LoginUserArgumentResolver
) : WebMvcConfigurer{

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(loginUserArgumentResolver)
    }
}
