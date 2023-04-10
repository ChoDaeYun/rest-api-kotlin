package com.daeyun.kotlinjava.domain.usertoken

import com.daeyun.kotlinjava.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="user_token")
class UserToken constructor(
    val userIdx: Long? = null,
    @Id
    val userToken: String = "",
):BaseEntity()