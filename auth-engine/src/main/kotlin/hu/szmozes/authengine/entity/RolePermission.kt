package hu.szmozes.authengine.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity

@Entity
class RolePermission {

    @EmbeddedId
    var id: RolePermissionId? = null
}
