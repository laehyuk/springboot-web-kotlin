package com.gowid.leo.common.domain.posts

import javax.persistence.*

@Entity
class Posts(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(length = 500, nullable = false)
        var title: String,

        @Column(columnDefinition = "TEXT", nullable = false)
        var content: String,

        var author: String

)
