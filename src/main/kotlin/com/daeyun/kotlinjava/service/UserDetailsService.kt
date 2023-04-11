package com.daeyun.kotlinjava.service

import com.daeyun.kotlinjava.domain.usertoken.UserToken
import com.daeyun.kotlinjava.domain.usertoken.UserTokenRepository
import com.daeyun.kotlinjava.dto.usertoken.AccountRes
import com.daeyun.kotlinjava.dto.usertoken.CustomUserDetails
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class UserDetailsService(
    private val tokenRepository: UserTokenRepository
) {

    fun makeToken() : String{
        val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        return List(16) {charset.random()}.joinToString("")
    }

    fun loadUserByUsername(accessToken: String): CustomUserDetails {
        var account: AccountRes? = tokenRepository.loadUserByUsername(accessToken) ?: throw AccessDeniedException("")
        var roles = ArrayList<GrantedAuthority>()
        roles.add(SimpleGrantedAuthority("USER"))
        return CustomUserDetails(
            username = account!!.idx.toString(),
            password = account!!.token,
            roles = roles,
            createDate = account.createDate
        )
    }

    fun updateToken(idx:Long , token:String){
        var userToken:UserToken = UserToken(idx,token)
        tokenRepository.save(userToken)
    }
}