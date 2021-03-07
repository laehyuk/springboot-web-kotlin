package com.gowid.leo.api.config.auth

import com.gowid.leo.common.domain.users.Users
import java.io.Serializable

data class SessionUser(
        private val name : String,
        private val email : String,
        private val picture : String
) : Serializable {
    constructor(user : Users) : this(user.name, user.email, user.picture)
}
