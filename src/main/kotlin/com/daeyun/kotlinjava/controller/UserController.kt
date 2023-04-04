package com.daeyun.kotlinjava.controller

import com.daeyun.kotlinjava.controller.response.CommonResult
import com.daeyun.kotlinjava.controller.response.ResponseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController : ResponseService(){

    @GetMapping("/")
    fun userList(): CommonResult? {
        return this.getSuccessResult()
    }
}