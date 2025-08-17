package hu.szmozes.authengine.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
class CompanyUserRole {

    @EmbeddedId
    var id: CompanyUserRoleId? = null

    @ManyToOne
    @MapsId("companyUserId")
    var companyUser: CompanyUser? = null

    @ManyToOne
    @MapsId("roleId")
    var role: Role? = null
}
