package com.gowid.leo.api.config.auth

import com.gowid.leo.common.domain.users.Users
import com.gowid.leo.common.domain.users.UsersRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpSession

@Service
class CustomOAuth2UserService(
        private val usersRepository: UsersRepository,
        private val httpSession: HttpSession
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val delegate = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)

        val registrationId = userRequest?.clientRegistration?.registrationId ?: ""
        val userNameAttributeName = userRequest?.clientRegistration?.providerDetails?.userInfoEndpoint?.userNameAttributeName ?: ""

        val attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.attributes)

        val user = saveOrUpdate(attributes)

        httpSession.setAttribute("user", SessionUser(user))

        return DefaultOAuth2User(
                Collections.singleton(SimpleGrantedAuthority(user.getRoleKey())),
                attributes.attributes,
                attributes.nameAttributeKey
        )
    }

    private fun saveOrUpdate(attributes: OAuthAttributes) : Users {
        usersRepository.findByEmail(attributes.email)?.let { user ->
            user.update(name = attributes.name, picture = attributes.picture)
            return user
        }

        return usersRepository.save(attributes.toEntity())
    }

}
