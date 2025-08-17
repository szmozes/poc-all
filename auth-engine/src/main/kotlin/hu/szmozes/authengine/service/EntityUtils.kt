package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.CompanySubscription
import hu.szmozes.authengine.entity.CompanySubscriptionId
import hu.szmozes.authengine.entity.CompanyUser
import hu.szmozes.authengine.entity.CompanyUserId
import hu.szmozes.authengine.entity.CompanyUserRole
import hu.szmozes.authengine.entity.CompanyUserRoleId
import hu.szmozes.authengine.entity.Permission
import hu.szmozes.authengine.entity.Role
import hu.szmozes.authengine.entity.RolePermission
import hu.szmozes.authengine.entity.RolePermissionId
import hu.szmozes.authengine.entity.Subscription
import hu.szmozes.authengine.entity.SubscriptionPermission
import hu.szmozes.authengine.entity.SubscriptionPermissionId
import hu.szmozes.authengine.entity.User

object EntityUtils {
    fun toCompanyUser(company: Company, user: User): CompanyUser {
        return CompanyUser().also {
            it.id = CompanyUserId(company.id!!, user.id!!)
            it.company = company
            it.user = user
        }
    }

    fun toCompanyUserRole(companyUser: CompanyUser, role: Role): CompanyUserRole {
        return CompanyUserRole().also {
            it.id = CompanyUserRoleId(companyUser.id!!, role.id!!)
            it.companyUser = companyUser
            it.role = role
        }
    }

    fun toRolePermission(role: Role, permission: Permission): RolePermission {
        return RolePermission().also {
            it.id = RolePermissionId(permission.id!!, role.id!!)
            it.role = role
            it.permission = permission
        }
    }

    fun toSubscriptionPermission(subscription: Subscription, permission: Permission): SubscriptionPermission {
        return SubscriptionPermission().also {
            it.id = SubscriptionPermissionId(subscription.id!!, permission.id!!)
            it.subscription = subscription
            it.permission = permission
        }
    }

    fun toCompanySubscription(company: Company, subscription: Subscription): CompanySubscription {
        return CompanySubscription().also {
            it.id = CompanySubscriptionId(company.id!!, subscription.id!!)
            it.company = company
            it.subscription = subscription
        }
    }

}