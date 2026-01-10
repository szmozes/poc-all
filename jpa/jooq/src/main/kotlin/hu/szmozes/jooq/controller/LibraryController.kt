package hu.szmozes.jooq.controller

import hu.szmozes.jooq.model.AuthorDto
import hu.szmozes.jooq.model.BookDto
import hu.szmozes.jooq.model.BookWithAuthorDto
import hu.szmozes.jooq.service.LibraryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LibraryController(private val libraryService: LibraryService) {

    @PostMapping("/authors")
    fun createAuthor(@RequestBody author: AuthorDto): Long {
        return libraryService.createAuthor(author.firstName, author.lastName)
    }

    @GetMapping("/authors")
    fun getAuthors(): List<AuthorDto> {
        return libraryService.getAuthors()
    }

    @PostMapping("/books")
    fun createBook(@RequestBody book: BookDto): Long {
        return libraryService.createBook(book.title, book.authorId)
    }

    @GetMapping("/books")
    fun getBooks(): List<BookWithAuthorDto> {
        return libraryService.getAllBooks()
    }
}
