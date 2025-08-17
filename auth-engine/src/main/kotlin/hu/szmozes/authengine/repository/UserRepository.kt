package hu.szmozes.authengine.repository

import hu.szmozes.authengine.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByName(userName: String): User?
}
