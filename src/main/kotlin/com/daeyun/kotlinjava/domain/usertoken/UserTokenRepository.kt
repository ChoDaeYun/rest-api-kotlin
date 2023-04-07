package com.daeyun.kotlinjava.domain.usertoken

import org.springframework.data.jpa.repository.JpaRepository

interface UserTokenRepository :JpaRepository<UserToken,String>{

}