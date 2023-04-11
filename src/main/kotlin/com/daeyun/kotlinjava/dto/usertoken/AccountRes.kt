package com.daeyun.kotlinjava.dto.usertoken

import com.querydsl.core.annotations.QueryProjection
import lombok.Data
import lombok.Getter

data class AccountRes @QueryProjection constructor(
    val idx:Long,
    val id:String,
    val name:String,
    val token:String,
)