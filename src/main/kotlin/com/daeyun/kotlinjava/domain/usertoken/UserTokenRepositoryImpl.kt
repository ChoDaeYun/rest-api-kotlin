package com.daeyun.kotlinjava.domain.usertoken

import com.daeyun.kotlinjava.dto.usertoken.CustomUserDetails
import com.daeyun.kotlinjava.dto.usertoken.QCustomUserDetails
import com.querydsl.jpa.impl.JPAQueryFactory
import javax.persistence.EntityManager
import com.daeyun.kotlinjava.domain.usertoken.QUserToken

class UserTokenRepositoryImpl(
    entityManager: EntityManager
) : UserTokenRepositoryCustom {
    private var queryFactory: JPAQueryFactory? = null
    init {
        queryFactory = JPAQueryFactory(entityManager)
    }

    fun findByaAccessTokenToCustomUserDetails(accessToken: UserToken) : CustomUserDetails? {
        val userToken = QUserToken.userToken1
        return null;
    }
}