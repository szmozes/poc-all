package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.Subscription
import org.springframework.data.jpa.repository.JpaRepository
import java.lang.Long

interface SubscriptionRepository : JpaRepository<Subscription, Long>
