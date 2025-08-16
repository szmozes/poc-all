package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.CompanyUserRole
import hu.szmozes.authengine.entity.CompanyUserRoleId
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyUserRoleRepository : JpaRepository<CompanyUserRole, CompanyUserRoleId>
