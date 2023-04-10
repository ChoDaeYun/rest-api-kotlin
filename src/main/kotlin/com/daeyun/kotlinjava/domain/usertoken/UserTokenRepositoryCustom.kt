package com.daeyun.kotlinjava.domain.usertoken

import com.daeyun.kotlinjava.dto.usertoken.AccountRes
import com.daeyun.kotlinjava.dto.usertoken.CustomUserDetails

interface UserTokenRepositoryCustom {
    fun loadUserByUsername(accessToken: String): AccountRes?
}