package hu.szmozes.authengine.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity

@Entity
class CompanyUser {

    @EmbeddedId
    var id: CompanyUserId? = null

}
