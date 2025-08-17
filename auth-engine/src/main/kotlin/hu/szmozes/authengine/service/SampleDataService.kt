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
import hu.szmozes.authengine.repository.CompanyRepository
import hu.szmozes.authengine.repository.CompanySubscriptionRepository
import hu.szmozes.authengine.repository.CompanyUserRepository
import hu.szmozes.authengine.repository.CompanyUserRoleRepository
import hu.szmozes.authengine.repository.PermissionRepository
import hu.szmozes.authengine.repository.RolePermissionRepository
import hu.szmozes.authengine.repository.RoleRepository
import hu.szmozes.authengine.repository.SubscriptionPermissionRepository
import hu.szmozes.authengine.repository.SubscriptionRepository
import hu.szmozes.authengine.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SampleDataService(
    private val companyRepository: CompanyRepository,
    private val userRepository: UserRepository,
    private val companyUserRepository: CompanyUserRepository,
    private val roleRepository: RoleRepository,
    private val companyUserRoleRepository: CompanyUserRoleRepository,
    private val permissionRepository: PermissionRepository,
    private val rolePermissionRepository: RolePermissionRepository,
    private val subscriptionRepository: SubscriptionRepository,
    private val subscriptionPermissionRepository: SubscriptionPermissionRepository,
    private val companySubscriptionRepository: CompanySubscriptionRepository,
) {

    fun loadSampleData() {
        companyRepository.deleteAll()
        userRepository.deleteAll()
        permissionRepository.deleteAll()
        roleRepository.deleteAll()
        subscriptionRepository.deleteAll()

        val company1 = Company().apply {
            name = "Alno Ltd."
        }
        companyRepository.save(company1)
        val company2 = Company().apply {
            name = "Brui Ltd."
        }
        companyRepository.save(company2)

        val user1 = User().apply {
            name = "Alice"
        }
        userRepository.save(user1)
        val user2 = User().apply {
            name = "Bob"
        }
        userRepository.save(user2)

        val companyUser1 = CompanyUser().apply {
            id = CompanyUserId(company1.id!!, user1.id!!)
        }
        companyUserRepository.save(companyUser1)
        val companyUser2 = CompanyUser().apply {
            id = CompanyUserId(company2.id!!, user1.id!!)
        }
        companyUserRepository.save(companyUser2)
        val companyUser3 = CompanyUser().apply {
            id = CompanyUserId(company1.id!!, user2.id!!)
        }
        companyUserRepository.save(companyUser3)

        val role1 = Role().apply {
            name = "admin"
        }
        roleRepository.save(role1)
        val role2 = Role().apply {
            name = "accountant"
        }
        roleRepository.save(role2)

        val companyUserRole1 = CompanyUserRole().apply {
            id = CompanyUserRoleId(companyUser1.id!!, role1.id!!)
        }
        companyUserRoleRepository.save(companyUserRole1)
        val companyUserRole2 = CompanyUserRole().apply {
            id = CompanyUserRoleId(companyUser3.id!!, role2.id!!)
        }
        companyUserRoleRepository.save(companyUserRole2)

        val permission1 = Permission().apply {
            name = "view_admin_dashboard"
        }
        permissionRepository.save(permission1)
        val permission2 = Permission().apply {
            name = "view_invoices"
        }
        permissionRepository.save(permission2)
        val permission3 = Permission().apply {
            name = "view_premium_settings"
        }
        permissionRepository.save(permission3)

        val rolePermission11 = RolePermission().apply {
            id = RolePermissionId(role1.id!!, permission1.id!!)
        }
        rolePermissionRepository.save(rolePermission11)
        val rolePermission12 = RolePermission().apply {
            id = RolePermissionId(role1.id!!, permission2.id!!)
        }
        rolePermissionRepository.save(rolePermission12)
        val rolePermission13 = RolePermission().apply {
            id = RolePermissionId(role1.id!!, permission3.id!!)
        }
        rolePermissionRepository.save(rolePermission13)
        val rolePermission22 = RolePermission().apply {
            id = RolePermissionId(role2.id!!, permission2.id!!)
        }
        rolePermissionRepository.save(rolePermission22)
        val rolePermission23 = RolePermission().apply {
            id = RolePermissionId(role2.id!!, permission3.id!!)
        }
        rolePermissionRepository.save(rolePermission23)

        val subscription1 = Subscription().apply {
            name = "free"
        }
        subscriptionRepository.save(subscription1)
        val subscription2 = Subscription().apply {
            name = "pro"
        }
        subscriptionRepository.save(subscription2)

        val subscriptionPermission11 = SubscriptionPermission().apply {
            id = SubscriptionPermissionId(subscription1.id!!, permission1.id!!)
        }
        subscriptionPermissionRepository.save(subscriptionPermission11)
        val subscriptionPermission12 = SubscriptionPermission().apply {
            id = SubscriptionPermissionId(subscription1.id!!, permission2.id!!)
        }
        subscriptionPermissionRepository.save(subscriptionPermission12)
        val subscriptionPermission21 = SubscriptionPermission().apply {
            id = SubscriptionPermissionId(subscription2.id!!, permission1.id!!)
        }
        subscriptionPermissionRepository.save(subscriptionPermission21)
        val subscriptionPermission22 = SubscriptionPermission().apply {
            id = SubscriptionPermissionId(subscription2.id!!, permission2.id!!)
        }
        subscriptionPermissionRepository.save(subscriptionPermission22)
        val subscriptionPermission23 = SubscriptionPermission().apply {
            id = SubscriptionPermissionId(subscription2.id!!, permission3.id!!)
        }
        subscriptionPermissionRepository.save(subscriptionPermission23)

        val companySubscription11 = CompanySubscription().apply {
            id = CompanySubscriptionId(company1.id!!, subscription1.id!!)
        }
        companySubscriptionRepository.save(companySubscription11)
        val companySubscription22 = CompanySubscription().apply {
            id = CompanySubscriptionId(company2.id!!, subscription2.id!!)
        }
        companySubscriptionRepository.save(companySubscription22)
    }
}
