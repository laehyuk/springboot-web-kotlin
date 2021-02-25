package com.gowid.leo.api.web.controller

import com.gowid.leo.api.web.dto.PostsSaveRequestDto
import com.gowid.leo.common.domain.posts.PostsRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.TestConstructor

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
internal class PostsApiControllerTests(
        @LocalServerPort private val port: Integer,
        private val restTemplate: TestRestTemplate,
        private val postsRepository: PostsRepository
){

    @AfterEach
    fun tearDown() = postsRepository.deleteAll()

    @Test
    fun `Posts_등록된다`() {
        //given
        val title = "title"
        val contents = "content"
        val requestDto = PostsSaveRequestDto(title, contents, "author")

        val url: String? = "http://localhost:$port/api/v1/posts"

        //when
        val responseEntity: ResponseEntity<Long>
                = restTemplate.postForEntity(url, requestDto, Long::class.java)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0)

        //then
        val posts = postsRepository.findAll()
        assertThat(posts[0].title).isEqualTo(title)
        assertThat(posts[0].content).isEqualTo(contents)
    }


}

