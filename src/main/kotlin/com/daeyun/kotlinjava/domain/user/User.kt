package com.daeyun.kotlinjava.domain.user

import com.daeyun.kotlinjava.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Entity

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idx")
    val idx: Int? = null,
    @Column(name="user_id")
    var userId: String = "",
    @Column(name="user_pw")
    var userPw: String = "",
    @Column(name="user_name")
    var userName: String = "",
):BaseEntity(){
}