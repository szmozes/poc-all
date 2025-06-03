package hu.szmozes.filterspecification.web

import hu.szmozes.filterspecification.entity.SampleEntity
import hu.szmozes.filterspecification.model.SearchRequest
import hu.szmozes.filterspecification.service.SampleService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/filter-specification")
class SearchRequestController(private val sampleService: SampleService) {

    private val logger = LoggerFactory.getLogger(SearchRequestController::class.java)

    @PostMapping("/search")
    fun search(@RequestBody searchRequest: SearchRequest): ResponseEntity<Page<SampleEntity>> {
        val search = sampleService.search(searchRequest)
        return ResponseEntity.ok(search)
    }

    @PostMapping
    fun create(@RequestBody sampleEntity: SampleEntity): ResponseEntity<SampleEntity> {
        val create = sampleService.create(sampleEntity)
        return ResponseEntity.ok(create)
    }
}