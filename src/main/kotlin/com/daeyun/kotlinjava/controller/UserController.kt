package com.daeyun.kotlinjava.controller

import com.daeyun.kotlinjava.controller.response.CommonResult
import com.daeyun.kotlinjava.controller.response.ResponseService
import com.daeyun.kotlinjava.exception.CustomException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController : ResponseService() {

    @GetMapping("/user/login")
    fun userList(): CommonResult? {
        if(1==1) throw CustomException("wet")
        return this.getSuccessResult()
    }
}