package com.daeyun.kotlinjava.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity {

    //안되네 .. 나중에 보자 ..
    @CreatedDate
    @Column(name = "create_date", nullable = false, updatable = false)
    var createDate: LocalDateTime? = null
}