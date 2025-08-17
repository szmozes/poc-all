package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.Permission
import hu.szmozes.authengine.entity.User

interface PermissionProvider {
    fun getPermissions(user: User, company: Company): Set<Permission>
}
