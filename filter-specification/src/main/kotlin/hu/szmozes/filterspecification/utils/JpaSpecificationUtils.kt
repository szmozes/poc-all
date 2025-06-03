package hu.szmozes.filterspecification.utils

import hu.szmozes.filterspecification.model.SearchRequest
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

object JpaSpecificationUtils {

    fun <T> toJpaSpecification(specification: SearchRequest.Specification): Specification<T> {
        return Specification { root: Root<T>, query: CriteriaQuery<*>?, cb: CriteriaBuilder ->

            // List of predicates from filters
            val filterPredicates = specification.filters.mapNotNull { filter ->
                buildPredicate(filter, root, cb)
            }

            // Recursive predicates from nested specifications
            val nestedPredicates = specification.nestedSpecifications.map {
                toJpaSpecification<T>(it).toPredicate(root, query, cb)
            }

            val allPredicates = filterPredicates + nestedPredicates

            if (allPredicates.isEmpty()) {
                cb.conjunction() // No filters, match all
            } else {
                when (specification.logic) {
                    SearchRequest.Specification.Logic.AND -> cb.and(*allPredicates.toTypedArray())
                    SearchRequest.Specification.Logic.OR -> cb.or(*allPredicates.toTypedArray())
                }
            }
        }
    }

    private fun <T> getPath(root: Root<T>, propertyPath: String): Path<Any> {
        return propertyPath.split(".")
            .fold(root as Path<*>) { path, segment -> path.get<Any>(segment) } as Path<Any>
    }

    private fun <T> buildPredicate(
        filter: SearchRequest.Specification.Filter,
        root: Root<T>,
        cb: CriteriaBuilder
    ): Predicate? {
        val path = getPath(root, filter.propertyPath)

        return when (filter.matchStyle) {
            SearchRequest.Specification.Filter.MatchStyle.EXACT ->
                cb.equal(path, filter.propertyValue)

            SearchRequest.Specification.Filter.MatchStyle.CONTAINS ->
                cb.like(cb.lower(path.`as`(String::class.java)), "%${filter.propertyValue.lowercase()}%")

            SearchRequest.Specification.Filter.MatchStyle.STARTS_WITH ->
                cb.like(cb.lower(path.`as`(String::class.java)), "${filter.propertyValue.lowercase()}%")

            SearchRequest.Specification.Filter.MatchStyle.ENDS_WITH ->
                cb.like(cb.lower(path.`as`(String::class.java)), "%${filter.propertyValue.lowercase()}")

            SearchRequest.Specification.Filter.MatchStyle.GREATER_THAN ->
                // todo
                cb.equal(path, filter.propertyValue)
//                cb.greaterThan(path, filter.propertyValue)

            SearchRequest.Specification.Filter.MatchStyle.LESS_THAN ->
                // todo
                cb.equal(path, filter.propertyValue)
//                cb.lessThan(path, filter.propertyValue)

            SearchRequest.Specification.Filter.MatchStyle.NOT_EQUAL ->
                cb.notEqual(path, filter.propertyValue)

            SearchRequest.Specification.Filter.MatchStyle.IS_NULL ->
                cb.isNull(path)

            SearchRequest.Specification.Filter.MatchStyle.IS_NOT_NULL ->
                cb.isNotNull(path)

            SearchRequest.Specification.Filter.MatchStyle.IN -> {
                val values = filter.propertyValue.split(",").map { it.trim() }
                path.`in`(values)
            }
        }
    }
}

