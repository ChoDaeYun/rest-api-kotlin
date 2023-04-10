package com.daeyun.kotlinjava.domain.usertoken

import com.daeyun.kotlinjava.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="user_token")
class UserToken constructor(
    @Id
    val userIdx: Long? = null,
    val userToken: String = "",
):BaseEntity()