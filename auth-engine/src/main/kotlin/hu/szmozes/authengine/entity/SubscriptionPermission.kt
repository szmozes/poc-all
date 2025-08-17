package hu.szmozes.authengine.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToMany

@Entity
class SubscriptionPermission {

    @EmbeddedId
    var id: SubscriptionPermissionId? = null

    @ManyToOne
    @MapsId("subscriptionId")
    var subscription: Subscription? = null

    @ManyToOne
    @MapsId("permissionId")
    var permission: Permission? = null
}
