package com.gowid.leo.api.web.controller

import com.gowid.leo.api.web.dto.PostsSaveRequestDto
import com.gowid.leo.api.web.dto.PostsUpdateRequestDto
import com.gowid.leo.common.domain.posts.Posts
import com.gowid.leo.common.domain.posts.PostsRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
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
        val responseEntity: ResponseEntity<Object>
                = restTemplate.postForEntity(url, requestDto, Object::class.java)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isNotNull

        //then
        val posts = postsRepository.findAll()
        assertThat(posts[0].title).isEqualTo(title)
        assertThat(posts[0].content).isEqualTo(contents)
    }

    @Test
    fun `Posts_수정된다`() {
        //given
        val savedPosts = postsRepository.save(Posts(title = "title", content = "content", author = "author"))

        val updateId = savedPosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"
        val requestDto = PostsUpdateRequestDto(expectedTitle, expectedContent)
        val url: String? = "http://localhost:$port/api/v1/posts/$updateId"

        val requestEntity: HttpEntity<PostsUpdateRequestDto> = HttpEntity(requestDto)

        //when
        val responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Object::class.java)

        //then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isNotNull

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }


}

