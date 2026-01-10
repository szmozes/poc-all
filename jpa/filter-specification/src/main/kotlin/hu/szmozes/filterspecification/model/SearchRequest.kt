package hu.szmozes.filterspecification.model

data class SearchRequest(
    val pageNumber: Int = 0,
    val pageSize: Int = 20,
    val specification: Specification = Specification(),
    val orders: List<Order> = emptyList()
) {
    data class Specification(
        val filters: List<Filter> = emptyList(),
        val logic: Logic = Logic.AND,
        val nestedSpecifications: List<Specification> = emptyList()
    ) {
        enum class Logic {
            AND, OR
        }

        data class Filter(
            val propertyPath: String,
            val propertyValue: String,
            val matchStyle: MatchStyle = MatchStyle.EXACT
        ) {
            enum class MatchStyle {
                EXACT,
                CONTAINS,
                STARTS_WITH,
                ENDS_WITH,
                GREATER_THAN,
                LESS_THAN,
                IN,
                NOT_EQUAL,
                IS_NULL,
                IS_NOT_NULL
            }
        }
    }

    data class Order(
        val propertyPath: String,
        val orderDirection: Direction = Direction.ASC
    ) {
        enum class Direction {
            ASC, DESC
        }
    }
}
