package hu.szmozes.authengine.entity

import jakarta.persistence.Embeddable

@Embeddable
data class RolePermissionId(
    var roleId: Long,
    var permissionId: Long
)
