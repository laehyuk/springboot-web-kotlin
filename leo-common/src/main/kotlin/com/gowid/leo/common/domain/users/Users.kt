package com.gowid.leo.common.domain.users

import com.gowid.leo.common.domain.BaseTime
import javax.persistence.*

@Entity
class Users(name: String, email: String, picture: String, role: Role): BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?= null

    @Column(nullable = false)
    var name: String= name

    @Column(nullable = false)
    var email: String= email

    @Column
    var picture: String= picture

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role = role

    fun update(name: String, picture: String) : Users {
        this.name = name
        this.picture = picture

        return this
    }

    fun getRoleKey() = this.role.key

}