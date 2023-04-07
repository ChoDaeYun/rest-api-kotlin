package com.daeyun.kotlinjava.dto.usertoken

import com.querydsl.core.annotations.QueryProjection
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails @QueryProjection constructor (
    private var idx: String,
    private var id: String,
    private var name: String,
    private var accessToken: String,
    private var authorities: Collection<GrantedAuthority>
) : UserDetails{

    override fun getUsername(): String = idx

    override fun getPassword(): String = accessToken

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true
}
