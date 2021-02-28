package com.gowid.leo.api.web.dto

import com.gowid.leo.common.domain.posts.Posts

data class PostsSaveRequestDto(
    val title: String,
    val content: String,
    val author: String
){
    fun toEntity(): Posts = Posts(title = title, content = content, author = author)
}
