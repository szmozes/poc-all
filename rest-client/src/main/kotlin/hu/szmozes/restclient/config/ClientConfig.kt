package hu.szmozes.restclient.config

import hu.szmozes.restclient.client.PostClient
import org.springframework.context.annotation.Configuration
import org.springframework.web.service.registry.ImportHttpServices

@Configuration
@ImportHttpServices(
    group = "post-client",
    types = [PostClient::class],
)
class ClientConfig
