package hu.szmozes.authengine.model

data class SampleData(
    val companies: List<CompanyDto>,
    val users: List<UserDto>,
    val roles: List<RoleDto>,
    val permissions: List<PermissionDto>,
    val subscriptions: List<SubscriptionDto>,
    val companyUsers: List<CompanyUserDto>,
    val companyUserRoles: List<CompanyUserRoleDto>,
    val rolePermissions: List<RolePermissionDto>,
    val subscriptionPermissions: List<SubscriptionPermissionDto>,
    val companySubscriptions: List<CompanySubscriptionDto>
)

data class CompanyDto(val name: String)
data class UserDto(val name: String)
data class RoleDto(val name: String)
data class PermissionDto(val name: String)
data class SubscriptionDto(val name: String)

data class CompanyUserDto(val company: String, val user: String)
data class CompanyUserRoleDto(val companyUser: CompanyUserDto, val role: String)
data class RolePermissionDto(val role: String, val permission: String)
data class SubscriptionPermissionDto(val subscription: String, val permission: String)
data class CompanySubscriptionDto(val company: String, val subscription: String)
