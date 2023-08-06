package hu.szmozes.kotlingradle

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {

    @GetMapping("sample")
    fun getSample(): ResponseEntity<List<Int>> {
        return ResponseEntity.ok(listOf(0, 1, 2, 3, 4))
    }
}