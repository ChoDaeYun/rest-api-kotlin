package com.daeyun.kotlinjava.domain.usertoken

import com.daeyun.kotlinjava.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="user_token")
class UserToken constructor(
    val userIdx: Long? = null,
    @Id
    val userToken: String = "",
):BaseEntity()