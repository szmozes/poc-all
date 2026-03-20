package hu.szmozes.restclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestClientPocApp

fun main(args: Array<String>) {
    runApplication<RestClientPocApp>(*args)
}
