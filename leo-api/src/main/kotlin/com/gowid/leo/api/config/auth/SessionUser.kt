package com.gowid.leo.api.config.auth

import com.gowid.leo.common.domain.users.Users
import java.io.Serializable

data class SessionUser(
        val name : String,
        val email : String,
        val picture : String
) : Serializable {
    constructor(user : Users) : this(user.name, user.email, user.picture)
}
