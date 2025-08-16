package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.lang.Long

interface RoleRepository : JpaRepository<Role, Long>
