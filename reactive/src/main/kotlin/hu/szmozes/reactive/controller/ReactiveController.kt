package hu.szmozes.reactive.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import kotlin.random.Random

@RestController
class ReactiveController {

    @GetMapping("/imperative")
    fun imperative(): ResponseEntity<List<List<Int>>> {
        val results = lists()
        return ResponseEntity.ok(results)
    }

    private fun lists(): List<List<Int>> {
        return listOf(
            listOfInts(),
            listOfInts(),
            listOfInts(),
            listOfIntsError(),
            listOfInts()
        )
    }

    @GetMapping("/reactive")
    fun reactive(): Mono<ResponseEntity<List<List<Int>>>> {
        return mono().map { ResponseEntity.ok(it) }
    }

    private fun mono(): Mono<List<List<Int>>> {
        return Mono.zip(
            createBlockingMono { listOfInts() },
            createBlockingMono { listOfInts() },
            createBlockingMono { listOfInts() },
            createBlockingMono { listOfIntsError() },
            createBlockingMono { listOfInts() })
            .map { tuple ->
                listOf(tuple.t1, tuple.t2, tuple.t3, tuple.t4, tuple.t5)
            }
    }

    private fun <T> createBlockingMono(block: () -> T): Mono<T> {
        return Mono.fromCallable {
            block()
        }.subscribeOn(Schedulers.boundedElastic())
    }

    private fun listOfInts(): List<Int> {
        Thread.sleep(2000)
        return List(5) { Random.nextInt(1, 101) }
    }

    private fun listOfIntsError(): List<Int> {
        Thread.sleep(2000)
        if (true) {
            throw RuntimeException("Something went wrong!")
        }
        return List(5) { Random.nextInt(1, 101) }
    }
}
