package com.daeyun.kotlinjava.domain.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class User constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val userId: String = "",
    val userPw: String = "",
    val userName: String = "",
    val createDate: LocalDateTime = LocalDateTime.now(),
){

}