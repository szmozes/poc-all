package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.CompanyUser
import hu.szmozes.authengine.entity.CompanyUserId
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyUserRepository : JpaRepository<CompanyUser, CompanyUserId>
