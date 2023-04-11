package com.daeyun.kotlinjava.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Configuration
class JacksonConfig {
    @Bean
    fun jsonMapperJava8DateTimeModule(): SimpleModule {
        val module = SimpleModule()
        module.addSerializer(LocalDate::class.java, object : JsonSerializer<LocalDate>() {
            @Throws(IOException::class)
            override fun serialize(
                localDate: LocalDate, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
                jsonGenerator.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate))
            }
        })


        module.addSerializer(LocalTime::class.java, object : JsonSerializer<LocalTime>() {
            @Throws(IOException::class)
            override fun serialize(
                localTime: LocalTime, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
                jsonGenerator.writeString(DateTimeFormatter.ofPattern("HH:mm:ss").format(localTime))
            }
        })


        module.addSerializer(LocalDateTime::class.java, object : JsonSerializer<LocalDateTime>() {
            @Throws(IOException::class)
            override fun serialize(
                localDateTime: LocalDateTime, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
                jsonGenerator.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime))
            }
        })

        return module
    }
}