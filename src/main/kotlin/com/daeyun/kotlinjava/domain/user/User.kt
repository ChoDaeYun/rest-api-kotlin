package com.daeyun.kotlinjava.domain.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idx: Int? = null,
    val userId: String = "",
    val userPw: String = "",
    val userName: String = "",
    val createDate: LocalDateTime = LocalDateTime.now(),
){

}