package hu.szmozes.authengine.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class CompanySubscriptionId(
    val companyId: Long,
    val subscriptionId: Long
) : Serializable
