package hu.szmozes.filterspecification.web

import hu.szmozes.filterspecification.model.SearchRequest
import hu.szmozes.filterspecification.service.HeavyCpuLoadService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("filter-specification")
class SearchRequestController(private val heavyCpuLoadService: HeavyCpuLoadService) {

    private val logger = LoggerFactory.getLogger(SearchRequestController::class.java)

    @PostMapping("/search")
    fun getFibonacci(@RequestBody searchRequest: SearchRequest): ResponseEntity<Int> {
        return ResponseEntity.ok(0)
    }
}