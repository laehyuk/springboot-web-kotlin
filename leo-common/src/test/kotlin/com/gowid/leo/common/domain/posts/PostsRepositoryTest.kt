package com.gowid.leo.common.domain.posts

import com.gowid.leo.common.LeoCommonApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [LeoCommonApplication::class])
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

}