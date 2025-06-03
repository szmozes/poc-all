package hu.szmozes.filterspecification.utils

import hu.szmozes.filterspecification.model.SearchRequest
import hu.szmozes.filterspecification.model.SearchRequest.Order.Direction
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

object PagingUtils {
    fun toPageable(searchRequest: SearchRequest): Pageable {
        val orders = searchRequest.orders.map {
            val direction = if (it.orderDirection == Direction.ASC)
                Sort.Direction.ASC else Sort.Direction.DESC
            val order = Sort.Order(direction, it.propertyPath)
            order
        }
        return PageRequest.of(searchRequest.pageNumber, searchRequest.pageSize, Sort.by(orders))
    }
}
