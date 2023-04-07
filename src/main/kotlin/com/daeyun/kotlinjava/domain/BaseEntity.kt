package com.daeyun.kotlinjava.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import lombok.Getter
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Getter
@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity (
    @CreatedDate
    @Column(name = "create_date",updatable = false)
    var createDate: LocalDateTime? = null,
    @LastModifiedDate
    @Column(name = "update_date")
    var updateDate: LocalDateTime? = null
)