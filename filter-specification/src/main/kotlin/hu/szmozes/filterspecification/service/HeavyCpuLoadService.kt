package hu.szmozes.filterspecification.service

import hu.szmozes.filterspecification.entity.SampleEntity
import hu.szmozes.filterspecification.model.SearchRequest
import hu.szmozes.filterspecification.repository.SampleRepository
import hu.szmozes.filterspecification.utils.JpaSpecificationUtils
import hu.szmozes.filterspecification.utils.PagingUtils
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class SampleService(
    private val sampleRepository: SampleRepository
) {

    fun search(searchRequest: SearchRequest): Page<SampleEntity> {
        return sampleRepository.findAll(
            JpaSpecificationUtils.toJpaSpecification(searchRequest.specification),
            PagingUtils.toPageable(searchRequest)
        )
    }
}