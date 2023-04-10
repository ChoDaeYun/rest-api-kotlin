package com.daeyun.kotlinjava.dto.usertoken

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class CustomUserDetails constructor (
    private var username:String,
    private var password:String,
    private var roles: Collection<GrantedAuthority>? = null
) : UserDetails{

    fun getIdx():Long {
        return username.toLong()
    }
    override fun getUsername(): String? = username

    override fun getPassword(): String = password

    override fun getAuthorities(): Collection<GrantedAuthority>? = authorities

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true
}
