package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.Permission
import hu.szmozes.authengine.entity.User
import org.springframework.stereotype.Service

@Service
class SubscriptionPermissionProvider : PermissionProvider {

    override fun getPermissions(user: User, company: Company): Set<Permission> {
        val permissions = mutableSetOf<Permission>()
        company.companySubscriptions.forEach { companySubscription ->
            companySubscription.subscription!!.subscriptionPermissions.forEach { subscriptionPermission ->
                permissions.add(subscriptionPermission.permission!!)
            }
        }
        return permissions
    }
}