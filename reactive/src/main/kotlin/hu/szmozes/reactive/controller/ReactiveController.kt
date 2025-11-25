package hu.szmozes.reactive.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Hooks
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import kotlin.random.Random

@RestController
class ReactiveController {

    @GetMapping("/imperative")
    fun imperative(): ResponseEntity<List<List<Int>>> {
        Hooks.onOperatorDebug()
        val results = lists()
        return ResponseEntity.ok(results)
    }

    private fun lists(): List<List<Int>> {
        return listOf(
            random1().block()!!,
            random2().block()!!,
            random3().block()!!,
            random4().block()!!,
            random5().block()!!
        )
    }

    @GetMapping("/reactive")
    fun reactive(): Mono<ResponseEntity<List<List<Int>>>> {
        Hooks.onOperatorDebug()
        return mono().map { ResponseEntity.ok(it) }
    }

    private fun mono(): Mono<List<List<Int>>> {
        return Mono.zip(random1(), random2(), random3(), random4(), random5())
            .map { tuple ->
                listOf(tuple.t1, tuple.t2, tuple.t3, tuple.t4, tuple.t5)
            }
    }

    private fun <T> createBlockingMono(block: () -> T): Mono<T> {
        return Mono.fromCallable {
            block()
        }.subscribeOn(Schedulers.boundedElastic())
    }

    private fun random1(): Mono<List<Int>> {
        return createBlockingMono {
            Thread.sleep(2000)
            List(5) { Random.nextInt(1, 101) }
        }
    }

    private fun random2(): Mono<List<Int>> {
        return createBlockingMono {
            Thread.sleep(2000)
            List(5) { Random.nextInt(1, 101) }
        }
    }

    private fun random3(): Mono<List<Int>> {
        return createBlockingMono {
            Thread.sleep(2000)
            List(5) { Random.nextInt(1, 101) }
        }
    }

    private fun random4(): Mono<List<Int>> {
        return createBlockingMono {
            Thread.sleep(2000)
            if (true) {
                throw RuntimeException("Something went wrong!")
            }
            List(5) { Random.nextInt(1, 101) }
        }
    }

    private fun random5(): Mono<List<Int>> {
        return createBlockingMono {
            Thread.sleep(2000)
            List(5) { Random.nextInt(1, 101) }
        }
    }
}
