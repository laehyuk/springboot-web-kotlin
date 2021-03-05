package com.gowid.leo.common.domain.users

enum class Role(val key: String, val title: String) {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반사용자")
}