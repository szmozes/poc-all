package hu.szmozes.authengine.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null

    @OneToMany(mappedBy = "permission", cascade = [CascadeType.ALL])
    var rolePermissions: MutableList<RolePermission> = mutableListOf()

    @OneToMany(mappedBy = "permission", cascade = [CascadeType.ALL])
    var subscriptionPermissions: MutableList<SubscriptionPermission> = mutableListOf()
}
