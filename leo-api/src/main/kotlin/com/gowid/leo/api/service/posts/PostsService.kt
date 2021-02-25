package com.gowid.leo.api.service.posts

import com.gowid.leo.api.web.dto.PostsSaveRequestDto
import com.gowid.leo.common.domain.posts.PostsRepository
import org.springframework.stereotype.Service

@Service
class PostsService(private val postsRepository: PostsRepository) {

    fun save(requestDto: PostsSaveRequestDto): Long? = postsRepository.save(requestDto.toEntity()).id

}