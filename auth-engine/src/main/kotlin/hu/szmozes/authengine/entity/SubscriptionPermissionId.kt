package hu.szmozes.authengine.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class SubscriptionPermissionId(
    var subscriptionId: Long,
    var permissionId: Long
) : Serializable
