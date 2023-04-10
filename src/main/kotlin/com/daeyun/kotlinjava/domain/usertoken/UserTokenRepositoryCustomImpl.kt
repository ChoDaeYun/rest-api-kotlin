package com.daeyun.kotlinjava.domain.usertoken

import com.querydsl.jpa.impl.JPAQueryFactory
import com.daeyun.kotlinjava.domain.user.QUser.user
import com.daeyun.kotlinjava.domain.usertoken.QUserToken.userToken1
import com.daeyun.kotlinjava.dto.usertoken.AccountRes
import com.daeyun.kotlinjava.dto.usertoken.QAccountRes

class UserTokenRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
) : UserTokenRepositoryCustom {

    override fun loadUserByUsername(
        accessToken:String
    ): AccountRes? {
         return queryFactory.select(
             QAccountRes(user.idx,user.userId,user.userName, userToken1.userToken)
            )
            .from(userToken1)
            .leftJoin(user).on(user.idx.eq(userToken1.userIdx))
            .where(userToken1.userToken.eq(accessToken))
            .fetchOne()
    }
}