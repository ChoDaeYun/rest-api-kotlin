package com.daeyun.kotlinjava.service

import com.daeyun.kotlinjava.domain.usertoken.UserTokenRepository
import org.springframework.stereotype.Service


@Service
class TokenService(
    private val tokenRepository: UserTokenRepository
) {

    fun makeToken() : String{
        val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        return List(16) {charset.random()}.joinToString("")
    }
}