package com.daeyun.kotlinjava.domain.usertoken

import com.querydsl.jpa.impl.JPAQueryFactory

class UserTokenRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
) : UserTokenRepositoryCustom {

}