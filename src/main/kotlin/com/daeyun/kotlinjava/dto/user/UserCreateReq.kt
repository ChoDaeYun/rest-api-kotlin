package com.daeyun.kotlinjava.dto.user

data class UserCreateReq(
    val id: String,
    val pw: String,
    val name: String
)