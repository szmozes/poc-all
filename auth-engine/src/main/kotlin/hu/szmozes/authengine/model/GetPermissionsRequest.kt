package hu.szmozes.authengine.model

data class GetPermissionsRequest(
    val companyName: String,
    val userName: String,
)
