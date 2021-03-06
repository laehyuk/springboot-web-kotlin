package com.gowid.leo.common.domain.posts

import com.gowid.leo.common.domain.BaseTime
import javax.persistence.*

@Entity
class Posts(title: String, content: String, author: String) : BaseTime() {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null

        @Column(length = 500, nullable = false)
        var title: String = title

        @Column(columnDefinition = "TEXT", nullable = false)
        var content: String = content

        var author: String = author

        fun update(title: String, content: String) {
                this.title = title
                this.content = content
        }

}

