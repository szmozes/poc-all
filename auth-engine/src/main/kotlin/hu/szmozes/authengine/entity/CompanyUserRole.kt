package hu.szmozes.authengine.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity

@Entity
class CompanyUserRole {

    @EmbeddedId
    var id: CompanyUserRoleId? = null

}
