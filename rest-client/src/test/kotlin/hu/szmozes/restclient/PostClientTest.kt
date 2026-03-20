package hu.szmozes.restclient

import hu.szmozes.restclient.client.PostClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostClientTest {

    @Autowired
    private lateinit var postClient: PostClient

    @Test
    fun `should fetch all posts`() {
        val posts = postClient.getAllPosts()
        assertThat(posts).isNotEmpty
        assertThat(posts[0].id).isNotNull()
        println("First post: ${posts[0]}")
    }

    @Test
    fun `should fetch post by id`() {
        val post = postClient.getPostById(1)
        assertThat(post).isNotNull
        assertThat(post.id).isEqualTo(1)
        println("Post 1: $post")
    }
}
