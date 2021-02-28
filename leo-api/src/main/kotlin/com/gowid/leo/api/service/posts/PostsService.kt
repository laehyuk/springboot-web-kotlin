package com.gowid.leo.api.service.posts

import com.gowid.leo.api.web.dto.PostsResponseDto
import com.gowid.leo.api.web.dto.PostsSaveRequestDto
import com.gowid.leo.api.web.dto.PostsUpdateRequestDto
import com.gowid.leo.common.domain.posts.PostsRepository
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsService(private val postsRepository: PostsRepository) {

    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long? = postsRepository.save(requestDto.toEntity()).id

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long? {
        val posts = postsRepository.findByIdOrNull(id)
                ?: throw IllegalArgumentException("해당 게시글이 없습니다. id=$id")

        posts.update(requestDto.title, requestDto.content)

        return posts.id
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): PostsResponseDto {
        val entity = postsRepository.findByIdOrNull(id)
                ?: throw IllegalArgumentException("해당 게시글이 없습니다. id=$id")

        return PostsResponseDto(entity)
    }


}