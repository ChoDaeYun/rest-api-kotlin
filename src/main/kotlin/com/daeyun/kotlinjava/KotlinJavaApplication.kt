package com.daeyun.kotlinjava

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class KotlinJavaApplication

fun main(args: Array<String>) {
    runApplication<KotlinJavaApplication>(*args)
}
