package com.daeyun.kotlinjava.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository :JpaRepository<User,Long>{

    fun existsByUserId(id:String):Boolean
    fun findByUserIdAndUserPw(id:String,pw:String):User?
}