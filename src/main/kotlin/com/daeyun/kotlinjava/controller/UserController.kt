package com.daeyun.kotlinjava.controller

import com.daeyun.kotlinjava.controller.response.CommonResult
import com.daeyun.kotlinjava.controller.response.ResponseService
import com.daeyun.kotlinjava.controller.response.SingleResult
import com.daeyun.kotlinjava.domain.user.User
import com.daeyun.kotlinjava.dto.user.UserCreateReq
import com.daeyun.kotlinjava.dto.user.UserLoginReq
import com.daeyun.kotlinjava.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) : ResponseService() {


    @PostMapping("/user/create")
    fun userCreate(
        @RequestBody dto: UserCreateReq
    ):CommonResult?{
        userService.saveUser(dto)
        return this.getSuccessResult()
    }

    @PostMapping("/user/login")
    fun userLogin(
        @RequestBody dto : UserLoginReq
    ): SingleResult<User>? {
        return this.getSingleResult(userService.loginUser(dto))
    }
}