package com.daeyun.kotlinjava.service

import com.daeyun.kotlinjava.domain.usertoken.UserToken
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
//        return tokenRepository.loadUserByUsername(accessToken)
        return null
    }

    fun updateToken(idx:Long , token:String){
        var userToken:UserToken = UserToken(idx,token)
        tokenRepository.save(userToken)
    }
}