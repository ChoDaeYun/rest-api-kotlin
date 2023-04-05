package com.daeyun.kotlinjava.domain.usertoken

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name="user_token")
class UserToken constructor(
    @Id
    val userIdx: Long? = null,
    val userToken: String = "",
    val updateDate: LocalDateTime = LocalDateTime.now(),
){

}