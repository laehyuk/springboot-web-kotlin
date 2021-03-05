package com.gowid.leo.common.domain.users

import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<Users, Long> {

    fun findByEmail(email: String): Users?

}