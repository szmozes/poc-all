package hu.szmozes.restclient.client

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

data class Post(val id: Int, val title: String, val body: String, val userId: Int)

@HttpExchange($$"${client.baseUrl:https://jsonplaceholder.typicode.com}/posts")
interface PostClient {

    @GetExchange
    fun getAllPosts(): List<Post>

    @GetExchange("/{id}")
    fun getPostById(@PathVariable id: Int): Post
}
