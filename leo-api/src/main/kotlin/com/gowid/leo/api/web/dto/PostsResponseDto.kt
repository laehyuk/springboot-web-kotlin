package com.gowid.leo.api.web.dto

import com.gowid.leo.common.domain.posts.Posts

data class PostsResponseDto(val entity: Posts){
        val id: Long? = entity.id
        val title: String = entity.title
        val content: String = entity.content
        val author: String = entity.author
}
