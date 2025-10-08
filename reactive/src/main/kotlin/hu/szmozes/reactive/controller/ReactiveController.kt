package hu.szmozes.reactive.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
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
