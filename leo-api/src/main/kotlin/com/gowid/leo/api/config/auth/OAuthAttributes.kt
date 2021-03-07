package com.gowid.leo.api.config.auth

import com.gowid.leo.common.domain.users.Role
import com.gowid.leo.common.domain.users.Users

data class OAuthAttributes(
        val attributes : Map<String, Any>,
        val nameAttributeKey : String,
        val name : String,
        val email : String,
        val picture : String
) {
    fun toEntity() = Users(name = this.name, email = this.email, picture = this.picture, role = Role.USER)

    companion object {

        fun of(registrationId : String, userNameAttributeName : String, attributes : Map<String, Any>) : OAuthAttributes {
            return ofGoogle(userNameAttributeName, attributes)
        }

        private fun ofGoogle(userNameAttributeName: String, attributes: Map<String,Any>) : OAuthAttributes {

            return OAuthAttributes(
                    attributes = attributes,
                    nameAttributeKey = userNameAttributeName,
                    name = attributes["name"] as String,
                    email = attributes["email"] as String,
                    picture = attributes["picture"] as String
            )
        }
    }


}