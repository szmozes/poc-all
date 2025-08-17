package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.Permission
import hu.szmozes.authengine.entity.User
import org.springframework.stereotype.Service

@Service
class RolePermissionProvider : PermissionProvider {

    override fun getPermissions(user: User, company: Company): Set<Permission> = user.companyUsers
        .find { it.company!!.id == company.id }!!
        .companyUserRoles
        .map { it.role!! }
        .flatMap { it.rolePermissions }
        .map { it.permission!! }
        .toSet()
}