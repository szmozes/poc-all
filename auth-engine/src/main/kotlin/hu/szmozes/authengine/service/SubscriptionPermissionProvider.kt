package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.Permission
import hu.szmozes.authengine.entity.User
import org.springframework.stereotype.Service

@Service
class SubscriptionPermissionProvider : PermissionProvider {

    override fun getPermissions(user: User, company: Company): Set<Permission> =
        company.companySubscriptions
            .map { it.subscription!! }
            .flatMap { it.subscriptionPermissions }
            .map { it.permission!! }
            .toSet()
}