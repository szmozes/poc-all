package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.RolePermission
import hu.szmozes.authengine.entity.RolePermissionId
import org.springframework.data.jpa.repository.JpaRepository

interface RolePermissionRepository : JpaRepository<RolePermission, RolePermissionId>
