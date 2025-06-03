package hu.szmozes.filterspecification.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class SampleEntity(
    @Id
    val id: UUID,
    val field1: String,
    val field2: Int,
)
