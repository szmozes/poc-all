package hu.szmozes.jooq

import hu.szmozes.jooq.config.JooqConfig
import hu.szmozes.jooq.generated.tables.references.AUTHOR
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class JooqSqlRenderingTest {

    @Test
    fun testSqlRendering() {
        val settings = JooqConfig().jooqSettings()
        val dsl = DSL.using(SQLDialect.POSTGRES, settings)

        val sql = dsl.insertInto(AUTHOR)
            .set(AUTHOR.FIRST_NAME, "John")
            .set(AUTHOR.LAST_NAME, "Doe")
            .sql

        println("Rendered SQL: $sql")

        assertTrue(sql.contains("author"), "SQL should contain lowercase 'author'")
        assertTrue(sql.contains("first_name"), "SQL should contain lowercase 'first_name'")
        assertTrue(sql.contains("last_name"), "SQL should contain lowercase 'last_name'")
        assertTrue(!sql.contains("\"AUTHOR\""), "SQL should not contain quoted uppercase 'AUTHOR'")
    }

    @Test
    fun testDefaultSqlRenderingFailsForPostgres() {
        val dsl = DSL.using(SQLDialect.POSTGRES)

        val sql = dsl.insertInto(AUTHOR)
            .set(AUTHOR.FIRST_NAME, "John")
            .set(AUTHOR.LAST_NAME, "Doe")
            .sql

        println("Default Rendered SQL: $sql")

        // Default jOOQ for Postgres uses quoted uppercase if the metadata is uppercase
        assertTrue(sql.contains("\"AUTHOR\""), "Default SQL should contain quoted uppercase 'AUTHOR'")
    }
}
