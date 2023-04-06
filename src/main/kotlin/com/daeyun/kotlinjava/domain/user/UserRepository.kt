package com.daeyun.kotlinjava.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface UserRepository :JpaRepository<User,Long>{

    fun existsByIdx(idx:Long):Boolean
    fun existsByUserId(id:String):Boolean
    fun findByUserIdAndUserPw(id:String,pw:String):User?

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User a set" +
            " a.userName = ?2" +
            " where a.idx = ?1")
    fun updateUser(idx:Long,userName:String)
}