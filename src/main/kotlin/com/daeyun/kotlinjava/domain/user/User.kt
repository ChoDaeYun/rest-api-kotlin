package com.daeyun.kotlinjava.domain.user

import com.daeyun.kotlinjava.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idx")
    var idx: Long? = null,
    @Column(name="user_id")
    var userId: String = "",
    @Column(name="user_pw")
    var userPw: String = "",
    @Column(name="user_name")
    var userName: String = "",
):BaseEntity()