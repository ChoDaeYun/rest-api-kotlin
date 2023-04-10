package com.daeyun.kotlinjava.controller

import com.daeyun.kotlinjava.exception.AuthenticationEntryPointException
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/exception"])
class ExceptionController {
    @GetMapping(value = ["/entrypoint"])
    fun entrypointException() {
        throw AuthenticationEntryPointException("")
    }

    @GetMapping(value = ["/accessdenied"])
    fun accessdeniedException() {
        throw AccessDeniedException("")
    }
}