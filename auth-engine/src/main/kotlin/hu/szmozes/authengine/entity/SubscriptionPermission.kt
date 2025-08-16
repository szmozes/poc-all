package hu.szmozes.authengine.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity

@Entity
class SubscriptionPermission {

    @EmbeddedId
    var id: SubscriptionPermissionId? = null
}
