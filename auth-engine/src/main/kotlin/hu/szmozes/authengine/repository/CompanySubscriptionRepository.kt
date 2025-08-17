package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.CompanySubscription
import hu.szmozes.authengine.entity.CompanySubscriptionId
import org.springframework.data.jpa.repository.JpaRepository

interface CompanySubscriptionRepository : JpaRepository<CompanySubscription, CompanySubscriptionId>
