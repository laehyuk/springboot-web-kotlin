package com.gowid.leo.api.web.controller

import com.gowid.leo.api.service.posts.PostsService
import com.gowid.leo.api.web.dto.PostsResponseDto
import com.gowid.leo.api.web.dto.PostsSaveRequestDto
import com.gowid.leo.api.web.dto.PostsUpdateRequestDto
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
class PostsApiController(private val postsService: PostsService) {

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto : PostsSaveRequestDto): Long? = postsService.save(requestDto)

    @GetMapping("/api/v1/posts/{id}")
    fun findById(@PathVariable id: Long): PostsResponseDto? = postsService.findById(id)

    @PutMapping("/api/v1/posts/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody requestDto: PostsUpdateRequestDto): Long? = postsService.update(id, requestDto)

    @DeleteMapping("api/v1/posts/{id}")
    fun delete(@PathVariable id: Long) = postsService.delete(id)

}
