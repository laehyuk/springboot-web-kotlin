package com.gowid.leo.api.web.controller

import com.gowid.leo.api.service.posts.PostsService
import com.gowid.leo.api.web.dto.PostsSaveRequestDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostsApiController(private val postsService: PostsService) {

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto : PostsSaveRequestDto) : Long? = postsService.save(requestDto)

}