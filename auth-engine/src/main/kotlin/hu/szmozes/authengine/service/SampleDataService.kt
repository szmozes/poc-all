package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.CompanyUser
import hu.szmozes.authengine.entity.CompanyUserId
import hu.szmozes.authengine.entity.CompanyUserRole
import hu.szmozes.authengine.entity.CompanyUserRoleId
import hu.szmozes.authengine.entity.Role
import hu.szmozes.authengine.entity.User
import hu.szmozes.authengine.repository.CompanyRepository
import hu.szmozes.authengine.repository.CompanyUserRepository
import hu.szmozes.authengine.repository.CompanyUserRoleRepository
import hu.szmozes.authengine.repository.RoleRepository
import hu.szmozes.authengine.repository.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class SampleDataService(
    private val companyRepository: CompanyRepository,
    private val userRepository: UserRepository,
    private val companyUserRepository: CompanyUserRepository,
    private val roleRepository: RoleRepository,
    private val companyUserRoleRepository: CompanyUserRoleRepository,
) {
    @PostConstruct
    fun init() {
        companyRepository.deleteAll()
        userRepository.deleteAll()
        companyUserRepository.deleteAll()
        roleRepository.deleteAll()

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
            val companyId = CompanyUserId(company1.id!!, user1.id!!)
            id = CompanyUserRoleId(companyId, role1.id!!)
        }
        companyUserRoleRepository.save(companyUserRole1)
        val companyUserRole2 = CompanyUserRole().apply {
            val companyId = CompanyUserId(company1.id!!, user2.id!!)
            id = CompanyUserRoleId(companyId, role2.id!!)
        }
        companyUserRoleRepository.save(companyUserRole2)
    }
}
