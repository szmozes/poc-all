package hu.szmozes.authengine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthEngineApp

fun main(args: Array<String>) {
    runApplication<AuthEngineApp>(*args)
}
