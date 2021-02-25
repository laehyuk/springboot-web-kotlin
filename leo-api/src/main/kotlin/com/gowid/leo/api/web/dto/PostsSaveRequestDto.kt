package com.gowid.leo.api.web.dto

import com.gowid.leo.common.domain.posts.Posts

data class PostsSaveRequestDto(
    val title: String,
    val author: String,
    val content: String
){
    fun toEntity(): Posts = Posts(title = title, content = content, author = author)
}
