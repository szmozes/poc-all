package hu.szmozes.restclient.config

import hu.szmozes.restclient.client.PostClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient

@Configuration
class ClientConfig {

    @Bean
    fun postClient(@Value($$"${client.baseUrl:https://jsonplaceholder.typicode.com}") baseUrl: String): PostClient {
        val restClient = RestClient.builder().baseUrl(baseUrl).build()
        val adapter = RestClientAdapter.create(restClient)
        val factory = HttpServiceProxyFactory.builderFor(adapter).build()
        return factory.createClient<PostClient>()
    }
}
