package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.SubscriptionPermission
import hu.szmozes.authengine.entity.SubscriptionPermissionId
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionPermissionRepository : JpaRepository<SubscriptionPermission, SubscriptionPermissionId>
