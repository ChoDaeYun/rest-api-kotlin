package com.daeyun.kotlinjava.dto.usertoken

import com.querydsl.core.annotations.QueryProjection

data class AccountRes @QueryProjection constructor(
    private var idx:Long,
    private var id:String,
    private var name:String,
    private var token:String,
)