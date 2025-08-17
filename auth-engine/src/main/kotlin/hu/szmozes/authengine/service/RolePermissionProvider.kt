package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.Permission
import hu.szmozes.authengine.entity.User
import org.springframework.stereotype.Service

@Service
class RolePermissionProvider : PermissionProvider {

    override fun getPermissions(user: User, company: Company): Set<Permission> {
        val companyUser = user.companyUsers.find { companyUser -> companyUser.company!!.id == company.id }

        val permissions = mutableSetOf<Permission>()
        companyUser!!.companyUserRoles.forEach { companyUserRole ->
            companyUserRole.role!!.rolePermissions.forEach { rolePermission ->
                permissions.add(rolePermission.permission!!)
            }
        }
        return permissions
    }
}