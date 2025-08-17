package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.Permission
import hu.szmozes.authengine.entity.Role
import hu.szmozes.authengine.entity.Subscription
import hu.szmozes.authengine.entity.User
import hu.szmozes.authengine.model.SampleData
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
        clearDb()

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

        val companyUser1 = EntityUtils.toCompanyUser(company1, user1)
        companyUserRepository.save(companyUser1)
        val companyUser2 = EntityUtils.toCompanyUser(company2, user1)
        companyUserRepository.save(companyUser2)
        val companyUser3 = EntityUtils.toCompanyUser(company1, user2)
        companyUserRepository.save(companyUser3)

        val role1 = Role().apply {
            name = "admin"
        }
        roleRepository.save(role1)
        val role2 = Role().apply {
            name = "accountant"
        }
        roleRepository.save(role2)

        val companyUserRole1 = EntityUtils.toCompanyUserRole(companyUser1, role1)
        companyUserRoleRepository.save(companyUserRole1)
        val companyUserRole2 = EntityUtils.toCompanyUserRole(companyUser3, role2)
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

        val rolePermission11 = EntityUtils.toRolePermission(role1, permission1)
        rolePermissionRepository.save(rolePermission11)
        val rolePermission12 = EntityUtils.toRolePermission(role1, permission2)
        rolePermissionRepository.save(rolePermission12)
        val rolePermission13 = EntityUtils.toRolePermission(role1, permission3)
        rolePermissionRepository.save(rolePermission13)
        val rolePermission22 = EntityUtils.toRolePermission(role2, permission2)
        rolePermissionRepository.save(rolePermission22)
        val rolePermission23 = EntityUtils.toRolePermission(role2, permission3)
        rolePermissionRepository.save(rolePermission23)

        val subscription1 = Subscription().apply {
            name = "free"
        }
        subscriptionRepository.save(subscription1)
        val subscription2 = Subscription().apply {
            name = "pro"
        }
        subscriptionRepository.save(subscription2)

        val subscriptionPermission11 = EntityUtils.toSubscriptionPermission(subscription1, permission1)
        subscriptionPermissionRepository.save(subscriptionPermission11)
        val subscriptionPermission12 = EntityUtils.toSubscriptionPermission(subscription1, permission2)
        subscriptionPermissionRepository.save(subscriptionPermission12)
        val subscriptionPermission21 = EntityUtils.toSubscriptionPermission(subscription2, permission1)
        subscriptionPermissionRepository.save(subscriptionPermission21)
        val subscriptionPermission22 = EntityUtils.toSubscriptionPermission(subscription2, permission2)
        subscriptionPermissionRepository.save(subscriptionPermission22)
        val subscriptionPermission23 = EntityUtils.toSubscriptionPermission(subscription2, permission3)
        subscriptionPermissionRepository.save(subscriptionPermission23)

        val companySubscription12 = EntityUtils.toCompanySubscription(company1, subscription2)
        companySubscriptionRepository.save(companySubscription12)
        val companySubscription21 = EntityUtils.toCompanySubscription(company2, subscription1)
        companySubscriptionRepository.save(companySubscription21)
    }

    private fun clearDb() {
        companyRepository.deleteAll()
        userRepository.deleteAll()
        permissionRepository.deleteAll()
        roleRepository.deleteAll()
        subscriptionRepository.deleteAll()
    }

    fun loadInputData(sampleData: SampleData) {
        clearDb()

        // Save base entities
        val companies = sampleData.companies.associateBy(
            { it.name },
            { companyRepository.save(Company().apply { name = it.name }) }
        )
        val users = sampleData.users.associateBy(
            { it.name },
            { userRepository.save(User().apply { name = it.name }) }
        )
        val roles = sampleData.roles.associateBy(
            { it.name },
            { roleRepository.save(Role().apply { name = it.name }) }
        )
        val permissions = sampleData.permissions.associateBy(
            { it.name },
            { permissionRepository.save(Permission().apply { name = it.name }) }
        )
        val subscriptions = sampleData.subscriptions.associateBy(
            { it.name },
            { subscriptionRepository.save(Subscription().apply { name = it.name }) }
        )

        // Relationships
        val companyUsers = sampleData.companyUsers.associateBy(
            { it.company to it.user },
            { companyUserRepository.save(EntityUtils.toCompanyUser(companies[it.company]!!, users[it.user]!!)) }
        )

        sampleData.companyUserRoles.forEach {
            val companyUser = companyUsers[it.companyUser.company to it.companyUser.user]!!
            val role = roles[it.role]!!
            companyUserRoleRepository.save(EntityUtils.toCompanyUserRole(companyUser, role))
        }

        sampleData.rolePermissions.forEach {
            val role = roles[it.role]!!
            val permission = permissions[it.permission]!!
            rolePermissionRepository.save(EntityUtils.toRolePermission(role, permission))
        }

        sampleData.subscriptionPermissions.forEach {
            val sub = subscriptions[it.subscription]!!
            val permission = permissions[it.permission]!!
            subscriptionPermissionRepository.save(EntityUtils.toSubscriptionPermission(sub, permission))
        }

        sampleData.companySubscriptions.forEach {
            val company = companies[it.company]!!
            val sub = subscriptions[it.subscription]!!
            companySubscriptionRepository.save(EntityUtils.toCompanySubscription(company, sub))
        }
    }

}
