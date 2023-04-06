package com.daeyun.kotlinjava.controller

import com.daeyun.kotlinjava.controller.response.CommonResult
import com.daeyun.kotlinjava.controller.response.ResponseService
import com.daeyun.kotlinjava.controller.response.SingleResult
import com.daeyun.kotlinjava.domain.user.User
import com.daeyun.kotlinjava.dto.user.UserCreateReq
import com.daeyun.kotlinjava.dto.user.UserLoginReq
import com.daeyun.kotlinjava.dto.user.UserUpdateReq
import com.daeyun.kotlinjava.exception.RequestParamsException
import com.daeyun.kotlinjava.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) : ResponseService() {

    @GetMapping("/user/get")
    fun userGet(
        @RequestParam idx: Long
    ): SingleResult<User>? {
        return this.getSingleResult(userService.getUser(idx))
    }

    @PostMapping("/user/create")
    fun userCreate(
        @RequestBody dto: UserCreateReq
    ):CommonResult?{
        if(dto.name == "" || dto.id == "" || dto.pw == ""){
            throw RequestParamsException()
        }
        userService.saveUser(dto)
        return this.getSuccessResult()
    }

    @PostMapping("/user/login")
    fun userLogin(
        @RequestBody dto : UserLoginReq
    ): SingleResult<User>? {
        return this.getSingleResult(userService.loginUser(dto))
    }

    @PutMapping("/user/update/{idx}")
    fun userModify(
        @PathVariable idx : Long
        ,@RequestBody dto : UserUpdateReq
    ): CommonResult? {
        if(dto.name == ""){
            throw RequestParamsException()
        }
        userService.updateUser(idx,dto)
        return this.getSuccessResult()
    }
}