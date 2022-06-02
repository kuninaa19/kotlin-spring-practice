package com.koboot.koboot.repository

import com.koboot.koboot.entity.QUser.user
import com.koboot.koboot.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*

class CustomUserRepositoryImpl : QuerydslRepositorySupport(User::class.java), CustomUserRepository {
    override fun search(name: String): List<User> = from(user).where(user.name.eq(name)).fetch()
}

interface CustomUserRepository {
    fun search(name: String): List<User>
}

@Repository
interface UserRepository : JpaRepository<User, Long>, CustomUserRepository {
    fun findByEmail(email: String): Optional<User>
}