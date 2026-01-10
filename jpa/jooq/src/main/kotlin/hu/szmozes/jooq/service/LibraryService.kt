package hu.szmozes.jooq.service

import hu.szmozes.jooq.generated.tables.references.AUTHOR
import hu.szmozes.jooq.generated.tables.references.BOOK
import hu.szmozes.jooq.model.AuthorDto
import hu.szmozes.jooq.model.BookWithAuthorDto
import org.jooq.DSLContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LibraryService(private val dsl: DSLContext) {

    @Transactional
    fun createAuthor(firstName: String, lastName: String): Long {
        return dsl.insertInto(AUTHOR)
            .set(AUTHOR.FIRST_NAME, firstName)
            .set(AUTHOR.LAST_NAME, lastName)
            .returningResult(AUTHOR.ID)
            .fetchOne()?.value1() ?: throw RuntimeException("Failed to create author")
    }

    @Transactional
    fun createBook(title: String, authorId: Long): Long {
        return dsl.insertInto(BOOK)
            .set(BOOK.TITLE, title)
            .set(BOOK.AUTHOR_ID, authorId)
            .returningResult(BOOK.ID)
            .fetchOne()?.value1() ?: throw RuntimeException("Failed to create book")
    }

    fun getAllBooks(): List<BookWithAuthorDto> {
        return dsl.select(
            BOOK.ID,
            BOOK.TITLE,
            AUTHOR.FIRST_NAME,
            AUTHOR.LAST_NAME
        )
            .from(BOOK)
            .join(AUTHOR).on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
            .fetch { r ->
                BookWithAuthorDto(
                    id = r.get(BOOK.ID)!!,
                    title = r.get(BOOK.TITLE)!!,
                    authorFirstName = r.get(AUTHOR.FIRST_NAME)!!,
                    authorLastName = r.get(AUTHOR.LAST_NAME)!!
                )
            }
    }

    fun getAuthors(): List<AuthorDto> {
        return dsl.selectFrom(AUTHOR)
            .fetch { r ->
                AuthorDto(
                    id = r.id,
                    firstName = r.firstName!!,
                    lastName = r.lastName!!
                )
            }
    }
}
