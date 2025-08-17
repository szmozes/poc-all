package hu.szmozes.authengine.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class CompanyUserId(
    var companyId: Long,
    var userId: Long
) : Serializable
