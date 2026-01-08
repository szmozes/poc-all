package hu.szmozes.reactive.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import kotlin.random.Random

@RestController
class ListController {

    @GetMapping("/list")
    fun reactive(): Mono<ResponseEntity<List<List<Int>>>> {
        val numberOfLists = 100
        val listSizes = List(numberOfLists) { 2 }

        return Flux.fromIterable(listSizes)
            .flatMap { s ->
                Mono.fromCallable { getIntList(s) }
                    .subscribeOn(Schedulers.boundedElastic())
            }
            .collectList()
            .map { ResponseEntity.ok(it) }
    }

    private fun getIntList(size: Int): List<Int> {
        Thread.sleep(2000)
        return List(size) { Random.nextInt(1, 5) }
    }

    @GetMapping("/list/coroutine")
    suspend fun coroutine(): ResponseEntity<List<List<Int>>> {
        val numberOfLists = 100
        val listSizes = List(numberOfLists) { 2 }

        val results: List<List<Int>> = coroutineScope {
            listSizes.map { s ->
                async(Dispatchers.IO) { getIntList(s) }
            }.awaitAll()
        }
        return ResponseEntity.ok(results)
    }

}
