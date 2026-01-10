package hu.szmozes.jooq.model

data class AuthorDto(
    val id: Long?,
    val firstName: String,
    val lastName: String
)

data class BookDto(
    val id: Long?,
    val title: String,
    val authorId: Long
)

data class BookWithAuthorDto(
    val id: Long,
    val title: String,
    val authorFirstName: String,
    val authorLastName: String
)
