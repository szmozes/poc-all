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
        val results = listOf(
            random1(),
            random2(),
            random3(),
            random4(),
            random5()
        )
        return ResponseEntity.ok(results)
    }

    @GetMapping("/reactive")
    fun reactive(): Mono<ResponseEntity<List<List<Int>>>> {
        val mono1 = Mono.fromCallable { random1() }.subscribeOn(Schedulers.boundedElastic())
        val mono2 = Mono.fromCallable { random2() }.subscribeOn(Schedulers.boundedElastic())
        val mono3 = Mono.fromCallable { random3() }.subscribeOn(Schedulers.boundedElastic())
        val mono4 = Mono.fromCallable { random4() }.subscribeOn(Schedulers.boundedElastic())
        val mono5 = Mono.fromCallable { random5() }.subscribeOn(Schedulers.boundedElastic())

        return Mono.zip(mono1, mono2, mono3, mono4, mono5)
            .map { tuple ->
                ResponseEntity.ok(listOf(tuple.t1, tuple.t2, tuple.t3, tuple.t4, tuple.t5))
            }
    }

    private fun random1(): List<Int> {
        Thread.sleep(2000)
        return List(5) { Random.nextInt(1, 101) }
    }

    private fun random2(): List<Int> {
        Thread.sleep(2000)
        return List(5) { Random.nextInt(1, 101) }
    }

    private fun random3(): List<Int> {
        Thread.sleep(2000)
        return List(5) { Random.nextInt(1, 101) }
    }

    private fun random4(): List<Int> {
        Thread.sleep(2000)
        return List(5) { Random.nextInt(1, 101) }
    }

    private fun random5(): List<Int> {
        Thread.sleep(2000)
        return List(5) { Random.nextInt(1, 101) }
    }
}
