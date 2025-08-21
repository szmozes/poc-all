package hu.szmozes.authengine.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
class CompanySubscription {

    @Id
    var id: CompanySubscriptionId? = null

    @ManyToOne
    @MapsId("companyId")
    var company: Company? = null

    @ManyToOne
    @MapsId("subscriptionId")
    var subscription: Subscription? = null
}
