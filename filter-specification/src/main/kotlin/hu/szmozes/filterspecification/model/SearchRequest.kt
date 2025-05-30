package hu.szmozes.filterspecification.model

class SearchRequest {
    lateinit var pageNumber: String
    lateinit var pageSize: String
    lateinit var specification: Specification
    lateinit var orders: List<Order>

    class Specification {
        lateinit var filters: List<Filter>
        lateinit var logic: Logic
        lateinit var nestedSpecification: Specification

        enum class Logic {
            AND, OR
        }

        class Filter {
            lateinit var propertyPath: String
            lateinit var propertyValue: String
            lateinit var matchStyle: MatchStyle

            enum class MatchStyle {
                EXACT
            }
        }
    }

    class Order {
        lateinit var propertyPath: String
        lateinit var orderDirection: Direction

        enum class Direction {
            ASC, DESC
        }
    }
}
