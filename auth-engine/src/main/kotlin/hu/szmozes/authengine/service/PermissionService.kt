package hu.szmozes.authengine.service

import hu.szmozes.authengine.model.GetPermissionsRequest
import hu.szmozes.authengine.repository.CompanyRepository
import hu.szmozes.authengine.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PermissionService(
    private val companyRepository: CompanyRepository,
    private val userRepository: UserRepository,
    private val rolePermissionProvider: RolePermissionProvider,
    private val subscriptionPermissionProvider: SubscriptionPermissionProvider,
) {

    @Transactional
    fun getPermissions(request: GetPermissionsRequest): List<String> {
        val company = companyRepository.findByName(request.companyName)
        val user = userRepository.findByName(request.userName)

        val permissionsFromUserRole = rolePermissionProvider.getPermissions(user!!, company!!)
        val permissionsFromCompanySubscription = subscriptionPermissionProvider.getPermissions(user, company)

        val intersection = permissionsFromUserRole intersect permissionsFromCompanySubscription
        return intersection.map { it.name!! }.toList()
    }
}
