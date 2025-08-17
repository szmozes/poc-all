package hu.szmozes.authengine.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinColumns
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
class CompanyUserRole {

    @EmbeddedId
    var id: CompanyUserRoleId? = null

    @ManyToOne
    @MapsId("companyUserId")
    @JoinColumns(
        JoinColumn(name = "company_id", referencedColumnName = "company_id"),
        JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    var companyUser: CompanyUser? = null

    @ManyToOne
    @MapsId("roleId")
    var role: Role? = null
}
