package hu.szmozes.authengine.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
class RolePermission {

    @EmbeddedId
    var id: RolePermissionId? = null

    @ManyToOne
    @MapsId("roleId")
    var role: Role? = null

    @ManyToOne
    @MapsId("permissionId")
    var permission: Permission? = null
}
