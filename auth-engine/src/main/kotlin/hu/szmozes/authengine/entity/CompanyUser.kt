package hu.szmozes.authengine.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToMany

@Entity
class CompanyUser {

    @EmbeddedId
    var id: CompanyUserId? = null

    @ManyToOne
    @MapsId("companyId")
    var company: Company? = null

    @ManyToOne
    @MapsId("userId")
    var user: User? = null

    @OneToMany(mappedBy = "companyUser", cascade = [CascadeType.ALL])
    var companyUserRoles: MutableList<CompanyUserRole> = mutableListOf()

}
