package hu.szmozes.authengine.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class CompanyUserRoleId(
    var companyUserId: CompanyUserId,
    var roleId: Long
) : Serializable
