package hu.szmozes.authengine.service

import hu.szmozes.authengine.entity.Company
import hu.szmozes.authengine.entity.CompanyUser
import hu.szmozes.authengine.entity.CompanyUserId
import hu.szmozes.authengine.entity.User
import hu.szmozes.authengine.repository.CompanyRepository
import hu.szmozes.authengine.repository.CompanyUserRepository
import hu.szmozes.authengine.repository.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class SampleDataService(
    private val companyRepository: CompanyRepository,
    private val userRepository: UserRepository,
    private val companyUserRepository: CompanyUserRepository,
) {
    @PostConstruct
    fun init() {
        companyRepository.deleteAll()
        userRepository.deleteAll()
        companyUserRepository.deleteAll()

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
            id = CompanyUserId(company1.id!!, company2.id!!)
        }
        companyUserRepository.save(companyUser1)
    }
}
