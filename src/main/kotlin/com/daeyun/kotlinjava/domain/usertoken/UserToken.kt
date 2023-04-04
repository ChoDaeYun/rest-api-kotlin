package com.daeyun.kotlinjava.domain.usertoken

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class UserToken constructor(
    @Id
    val userIdx: Long? = null,
    val userToken: String = "",
    val updateDate: LocalDateTime = LocalDateTime.now(),
){

}