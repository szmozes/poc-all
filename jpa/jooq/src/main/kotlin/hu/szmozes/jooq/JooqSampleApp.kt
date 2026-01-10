package hu.szmozes.jooq

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JooqSampleApp

fun main(args: Array<String>) {
    runApplication<JooqSampleApp>(*args)
}
