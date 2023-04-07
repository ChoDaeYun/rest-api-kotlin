package com.daeyun.kotlinjava.service

import com.daeyun.kotlinjava.domain.usertoken.UserTokenRepository
import com.daeyun.kotlinjava.dto.usertoken.CustomUserDetails
import org.springframework.stereotype.Service


@Service
class UserDetailsService(
    private val tokenRepository: UserTokenRepository
) {

    fun makeToken() : String{
        val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        return List(16) {charset.random()}.joinToString("")
    }

    fun loadUserByUsername(accessToken:String) : CustomUserDetails? {
//        tokenRepository.findByaAccessTokenToCustomUserDetails(accessToken)
        return null
    }
}