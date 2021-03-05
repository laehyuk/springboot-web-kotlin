package com.gowid.leo.common.domain.posts

import com.gowid.leo.common.LeoCommonApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [LeoCommonApplication::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class PostsRepositoryTest (private val postsRepository: PostsRepository) {

    @BeforeEach
    fun cleanUp() = postsRepository.deleteAll()

    @Test
    @DisplayName("게시글 저장_불러오기 테스트")
    fun saveAndFind(){
        //given
        val title = "테스트 게시글"
        val content = "테스트 본문"
        val author = "lhjang@gowid.com"

        postsRepository.save(Posts(title = title, content = content, author = author))

        //when
        val posts = postsRepository.findAll()

        //then
        assertThat(posts).hasSize(1)
        val post = posts[0]
        assertThat(post).isNotNull
        assertThat(post.title).isEqualTo(title)
        assertThat(post.content).isEqualTo(content)
        assertThat(post.author).isEqualTo(author)
    }

    @Test
    @DisplayName("BaseTimeEntity_등록")
    fun registerBaseTimeEntity(){
        //given
        val now = LocalDateTime.of(2021,3,5,0,0,0)
        postsRepository.save(Posts("title","content","author"))

        //when
        val postsList = postsRepository.findAll()

        //then
        val posts: Posts = postsList[0]

        println(">>>>>>>> createDate= ${posts.createdDate}, modifiedDate=${posts.modifiedDate}")

        assertThat(posts.createdDate).isAfter(now)
        assertThat(posts.modifiedDate).isAfter(now)
    }

}