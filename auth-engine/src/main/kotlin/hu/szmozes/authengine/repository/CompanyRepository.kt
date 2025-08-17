package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, Long>{
    fun findByName(companyName: String): Company?
}
