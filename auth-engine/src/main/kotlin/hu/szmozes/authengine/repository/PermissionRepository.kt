package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.Permission
import org.springframework.data.jpa.repository.JpaRepository
import java.lang.Long

interface PermissionRepository : JpaRepository<Permission, Long> {
}
