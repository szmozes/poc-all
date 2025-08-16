package hu.szmozes.authengine.service

import hu.szmozes.authengine.model.GetPermissionsRequest
import org.springframework.stereotype.Service

@Service
class PermissionService() {
    fun getPermissions(getPermissionsRequest: GetPermissionsRequest): List<String> {
        return listOf()
    }
}
